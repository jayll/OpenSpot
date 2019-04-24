package com.example.openspot

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.MapFragment
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.preference.Preference
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class BookDrivewayActivity : AppCompatActivity(),OnMapReadyCallback{

    companion object {
        private const val TAG = "BookDrivewayActivity"
    }

    private var mMap: GoogleMap? = null
    private val db = FirebaseFirestore.getInstance()
    private val currentFirebaseUser = FirebaseAuth.getInstance().currentUser

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setTheme(R.style.FragmentTheme)
        setContentView(R.layout.activity_book_driveway)

        val mapFragment = fragmentManager
            .findFragmentById(R.id.map) as MapFragment
        mapFragment.getMapAsync(this)

        supportActionBar?.title = "Confirm Booking"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        val addressData : String? = extras?.getString("clickedMarkerAddress")
        val priceData : String? = extras?.getString("clickedMarkerPrices")
        var carArray: Any?
        var name = ""
        val usersCar = arrayListOf("Please select a vehicle")
        var carInfo :MutableList<String>
        val docRef = db.collection("Users").document(currentFirebaseUser!!.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    carArray = document.data!!["Cars"]
                    Log.d(VehicleViewActivity.TAG, "DocumentSnapshot dataaaa: " + carArray.toString())
                    carInfo = carArray as MutableList<String>
                    if (carInfo.isNotEmpty()) {
                        for (i in carInfo.indices) {
                            when (i%5) {
                                0 -> {
                                    name += carInfo[i]
                                }

                                1 -> {
                                    name += " " + carInfo[i] + ": "
                                }

                                4 -> {
                                    name += carInfo[i]
                                    usersCar.add(name)
                                    name = ""
                                }
                            }
                        }
                    }
                }
            }

        val markerAddress = findViewById<TextView>(R.id.address)
        markerAddress.text = addressData

        val markerPrice = findViewById<TextView>(R.id.price)
        markerPrice.text = "Price: $priceData"


        val currentSpinner : Spinner = findViewById(R.id.vehicleSpinner)
        val currentSpinnerDataAdapter = object : ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, usersCar) {
            override fun isEnabled(position:Int):(Boolean){
                return position != 0
            }

            override fun getDropDownView(position: Int, convertView: View?,
                                         parent: ViewGroup
            ): View? {
                val view = super.getDropDownView(position, convertView, parent)
                val tv = view as TextView
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY)
                } else {
                    tv.setTextColor(Color.BLACK)
                }
                return view
            }
        }
        currentSpinnerDataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        currentSpinner.adapter = currentSpinnerDataAdapter
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val extras = intent.extras
        val lat = extras!!.getDouble("clickedMarkerLatitude")
        val lng = extras.getDouble("clickedMarkerLongitude")

        mMap!!.uiSettings.isScrollGesturesEnabled = false
        mMap!!.uiSettings.isMyLocationButtonEnabled = false
        mMap!!.uiSettings.isZoomGesturesEnabled = false
        mMap!!.uiSettings.isZoomControlsEnabled = false
        mMap!!.uiSettings.isRotateGesturesEnabled = false
        mMap!!.uiSettings.isTiltGesturesEnabled = false
        mMap!!.uiSettings.isCompassEnabled = false

        mMap!!.setOnMapClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=$lat,$lng")
            )
            startActivity(intent)
        }


        val sydney = LatLng(lat,lng)
        mMap!!.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lng), 16f))
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        mMap!!.setOnMarkerClickListener { true }
    }

    private fun bookNowFunctionality(){
        var addressArray:Any?
        var counter = 0
        val extras = intent.extras
        val lat = extras!!.getDouble("clickedMarkerLatitude")
        val lng = extras.getDouble("clickedMarkerLongitude")
        val docRef = db.collection("Users")
        docRef.get()
            .addOnSuccessListener { result ->
                loop@ for (document in result) {
                    var tempLatitude = 0.0
                    var tempLongitude = 0.0
                    addressArray = document!!.data["Addresses"]
                    val a = addressArray as? MutableList<String>?
                    if (a != null) {
                        for (item in a.indices) {
                            when(item % 5){
                                1 -> {
                                    tempLatitude = a[item].toDouble()
                                }
                                2 -> {
                                    tempLongitude = a[item].toDouble()
                                }
                            }
                            counter++

                            if((tempLatitude == lat) && (tempLongitude == lng)){
                                Log.d(TAG, "HELLO::: $counter")
                                a[counter] = "0"
                                db.collection("Users").document(document.id)
                                    .update("Addresses", a)
                                    .addOnSuccessListener { documentReference ->
                                        val i = Intent(this@BookDrivewayActivity, NavigationActivity::class.java)
                                        startActivity(i)
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w(VehicleInfoActivity.TAG, "Error adding document", e)
                                    }
                                break@loop
                            }
                        }
                        counter = 0
                    }
                }
            }
    }

    fun bookNow(v:View){
        bookNowFunctionality()
    }
}
