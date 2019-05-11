package com.example.openspot

import android.content.Intent
import android.content.pm.ActivityInfo
import android.nfc.Tag
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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
        setTheme(R.style.FragmentTheme)
        setContentView(R.layout.activity_vehicle_view)
        supportActionBar?.title = "Vehicles"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        NavigationActivity.fromVehiclePage = true
        fm.replace(R.id.vehicleContainer, vehicle,"vehicleFragment").commit()



//        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
//        Toast.makeText(this, "" + currentFirebaseUser?.uid, Toast.LENGTH_SHORT).show()
    }

    // create an action bar button
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.driveway, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // handle button activities
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.drivewayAddButton) {
            VehicleInfoActivity.fromVehicleView = true
            val intent = Intent(this@VehicleViewActivity, VehicleInfoActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
