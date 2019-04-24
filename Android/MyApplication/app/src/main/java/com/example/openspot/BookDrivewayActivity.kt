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
        val docRef = db.collection("Users").document(currentFirebaseUser?.uid.toString())
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    carArray = document.data!!["Cars"]
                    Log.d(VehicleViewActivity.TAG, "DocumentSnapshot dataaaa: " + carArray.toString())
                    carInfo = carArray as MutableList<String>
                    if (carInfo.size > 5) {
                        for (i in 0..4) {
                            when (i) {
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
        markerPrice.text = "$"+priceData+"0/hr"

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

    fun bookNow(v:View){

    }






}
