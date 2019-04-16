package com.example.openspot

import android.content.Intent
import android.content.pm.ActivityInfo
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseUser



class VehicleViewActivity : AppCompatActivity() {
    private val vehicle : Fragment = VehicleFragment()
    private val fm = supportFragmentManager.beginTransaction()

    companion object {
        const val TAG = "VehicleViewActivity"
        var fromVehicleInfoPage = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_vehicle_view)

//        if(fromVehicleInfoPage){
            fm.replace(R.id.pref_container, vehicle,"vehicleFragment").commit()
//        }

//        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
//        Toast.makeText(this, "" + currentFirebaseUser?.uid, Toast.LENGTH_SHORT).show()
    }

    fun addVehicle(v: View){
        VehicleInfoActivity.fromVehicleView = true
        val i = Intent(this@VehicleViewActivity, VehicleInfoActivity::class.java)
        startActivity(i)
    }

    fun closeButton(v:View){
        NavigationActivity.fromVehiclePage = true
        val i = Intent(this@VehicleViewActivity, NavigationActivity::class.java)
        startActivity(i)
    }
}
