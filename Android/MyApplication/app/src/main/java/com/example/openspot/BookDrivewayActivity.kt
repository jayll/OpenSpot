package com.example.openspot

import android.content.pm.ActivityInfo
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.fragment_home.*

class BookDrivewayActivity : AppCompatActivity() {

    companion object {
        private const val MY_LOCATION_REQUEST_CODE = 1
    }

    //map vars
//    var markerPin: Marker? = null
//    private lateinit var mMap: GoogleMap
//    private var gMapView: MapView? = null
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private lateinit var lastLocation: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setTheme(R.style.FragmentTheme)
        setContentView(R.layout.activity_book_driveway)

        supportActionBar?.title = "Confirm Booking"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //delete if not needed
//            gMapView = findViewById(R.id.mapViewBooking) as MapView
//            mapView.onCreate(savedInstanceState)
//            mapView.onResume()
//            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val vehicleSpinner: Spinner = findViewById(R.id.vehicleSpinner)
        vehicleSpinner.prompt = "--Select--"
    }






}
