package com.example.openspot


import android.Manifest
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.graphics.*
import android.location.Location
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.preference.PreferenceFragmentCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.openspot.DrivewayViewActivity.Companion.TAG
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.common.api.Status
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_home.*
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
//import org.jetbrains.anko.dip
import java.io.BufferedInputStream
import java.io.InputStream
import java.util.*


class ReservationFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        return   inflater.inflate(R.layout.reservation, container, false)
    }
}

class HomeFragment : Fragment(),OnMapReadyCallback{

    companion object {
        private const val MY_LOCATION_REQUEST_CODE = 1
    }

    var markerPin:Marker? = null
    private lateinit var mMap: GoogleMap
    private var gMapView: MapView? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var lastLocation: Location
    private val db = FirebaseFirestore.getInstance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        autoCompletePrediction()
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        if(activity != null) {
            gMapView = view!!.findViewById(R.id.mapView) as MapView
            mapView.onCreate(savedInstanceState)
            mapView.onResume()
            mapView.getMapAsync(this)
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity!!)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        autoCompletePrediction()
        checkPermission()
        availableDriveways()
    }
    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                context!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                MY_LOCATION_REQUEST_CODE
            )
            return
        } else {
            Log.e("DB", "PERMISSION GRANTED")
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(activity!!) { location ->
            // Got last known location. In some rare situations this can be null.
            // 3
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.size == 1 &&
                permissions[0] == Manifest.permission.ACCESS_FINE_LOCATION &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                checkPermission()
                mMap.isMyLocationEnabled = true
                fusedLocationClient.lastLocation.addOnSuccessListener(activity!!) { location ->
                    // Got last known location. In some rare situations this can be null.
                    // 3
                    if (location != null) {
                        lastLocation = location
                        val currentLatLng = LatLng(location.latitude, location.longitude)
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 16f))
                    }
                }
            } else {
                // Permission was denied. Display an error message.
                val view =gMapView!!.rootView.findViewById<MapView>(R.id.mapView)
                Snackbar.make(view,"No current location without permission",Snackbar.LENGTH_SHORT).show()
            }
        }
    }
    private fun autoCompletePrediction() {
        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(activity!!.applicationContext, "AIzaSyDi9cU1TQ3J-M2sEty4HteQq_ttIaLrJMU")
        }
        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            childFragmentManager.findFragmentById(R.id.autocomplete_fragment2) as? AutocompleteSupportFragment

        autocompleteFragment?.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG))
        autocompleteFragment!!.view!!.setBackgroundColor(Color.WHITE)
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {

            override fun onPlaceSelected(p0: Place) {
                Log.d(ListDrivewayActivity.TAG, "Place: " + p0.name + ", " + p0.id)
                markerPin?.remove()
                val Address = p0.name
                val Latitude = p0.latLng!!.latitude
                val Longitude = p0.latLng!!.longitude
                markerPin = mMap.addMarker(MarkerOptions()
                    .position(LatLng(Latitude,Longitude))
                    .title(Address)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(Latitude,Longitude),16f))

            }

            override fun onError(p0: Status) {
                Log.d(ListDrivewayActivity.TAG, "An error occured: $p0")
            }
        })
    }
    private fun availableDriveways() {
        var addressArray:Any?
        var drivewayAddress = ""
        var drivewayLat = 0.0
        var drivewayLong = 0.0
        var drivewayPrice = 0.00
        var drivewayMarker: Marker
        val docRef = db.collection("Users")
        docRef.get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "HELLO:${document.id} => ${document.data}")
                    addressArray = document!!.data["Addresses"]
                    val a  = addressArray as? MutableList<String>?
                    if (a != null) {
                        for (item in a.indices) {
                            when(item % 5){
                                0 ->{
                                    drivewayAddress = a[item]
                                }
                                1 ->{
                                    drivewayLat = a[item].toDouble()
                                }
                                2 ->{
                                    drivewayLong = a[item].toDouble()
                                }

                                4->{
                                    drivewayPrice = a[item].toDouble()
                                    if(a[item-1] == "1"){
                                        val conf = Bitmap.Config.ARGB_8888
                                        val bmp = Bitmap.createBitmap(200, 150, conf)
                                        val canvas1 = Canvas(bmp)

                                        // paint defines the text color, stroke width and size
                                        val color = Paint()
                                        color.textSize = 40.0F
                                        color.isFakeBoldText = true
                                        color.color = Color.WHITE

                                        // modify canvas
                                        canvas1.drawBitmap(BitmapFactory.decodeResource(
                                            resources, R.drawable.google_maps_marker), 0f,0f, color)
                                        canvas1.drawText("$"+drivewayPrice+"0", 20f, 70f, color)

                                        drivewayMarker= mMap.addMarker(
                                                MarkerOptions()
                                                .position(LatLng(drivewayLat,drivewayLong))
                                                .title(drivewayAddress)
                                                .snippet("$"+drivewayPrice+"0/hr")
                                                .icon(BitmapDescriptorFactory.fromBitmap(bmp)))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        mMap.setOnInfoWindowClickListener { p0 ->
            val latLong = p0!!.position
            val intent = Intent(activity, BookDrivewayActivity::class.java)

            intent.putExtra("clickedMarkerLatitude", latLong.latitude)
            intent.putExtra("clickedMarkerLongitude", latLong.longitude)
            intent.putExtra("clickedMarkerPrices",p0.snippet)
            intent.putExtra("clickedMarkerAddress",p0.title)

//            Toast.makeText(
//                activity!!.baseContext,
//                "" + drivewayLat.toString(),
//                Toast.LENGTH_SHORT
//            ).show()
//            Toast.makeText(
//                activity!!.baseContext,
//                "" + drivewayLong.toString(),
//                Toast.LENGTH_SHORT
//            ).show()

            startActivity(intent)
        }
    }
}



class SettingFragment : PreferenceFragmentCompat() {

    //Firebase for User Profile title replacement with Users Full name
    private val db = FirebaseFirestore.getInstance()
    private val currentFirebaseUser = FirebaseAuth.getInstance().currentUser

    override fun onCreatePreferences(savedInstanceState: Bundle?, root_key: String?) {
        setPreferencesFromResource(R.xml.preferences, root_key)
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val logoutBtn = findPreference("logout")
        logoutBtn.setOnPreferenceClickListener {
            AuthUI.getInstance()
                .signOut(activity!!.baseContext)
                .addOnCompleteListener {
                    startActivity(Intent(activity, MainActivity::class.java))
                }
            true
        }

        val contactUsButton = findPreference("contact")
        contactUsButton.setOnPreferenceClickListener {
            AuthUI.getInstance()
            startActivity(Intent(activity, ContactActivity::class.java))
            true
        }
        val vehicleButton = findPreference("vehicle")
        vehicleButton.setOnPreferenceClickListener {
            AuthUI.getInstance()
            startActivity(Intent(activity, VehicleViewActivity::class.java))
            true
        }
        val userProfileBtn = findPreference("profile")
        if(currentFirebaseUser != null) {
            val docRef = db.collection("Users").document(currentFirebaseUser.uid)
            docRef.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        userProfileBtn.title = document.data!!["fullName"].toString()
                    }
                }
        }
        userProfileBtn.setOnPreferenceClickListener {
            AuthUI.getInstance()
            startActivity(Intent(activity, EditProfile::class.java))
            true
        }

        val listYourDriveWayBtn = findPreference("driveway")
        listYourDriveWayBtn.setOnPreferenceClickListener {
            AuthUI.getInstance()
            startActivity(Intent(activity, DrivewayViewActivity::class.java))
            true
        }
    }
}