package com.example.openspot

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity() {

    companion object {
        var fromVehiclePage = false
        var fromContactPage = false
        var fromEditProfile = false
        var fromDrivewayPage = false
    }

    private val fragment11: Fragment = HomeFragment()
    private val fragment22: Fragment = ReservationFragment()
    private val fragment33: Fragment = SettingFragment()
    private val fm = supportFragmentManager
    private var active = fragment11
    private val db = FirebaseFirestore.getInstance()


    private var mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                fm.beginTransaction().hide(active).show(fragment11).commit()
                active = fragment11
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_reservations -> {
                fm.beginTransaction().hide(active).show(fragment22).commit()
                active = fragment22
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings -> {
                fm.beginTransaction().hide(active).show(fragment33).commit()
                active = fragment33
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val user = FirebaseAuth.getInstance().currentUser
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setTheme(R.style.AppTheme)

        if (user == null) {
            val intent = Intent(this@NavigationActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        else {
            val docRef = db.collection("Users").document(user.uid)
            docRef.get()
                .addOnSuccessListener { document ->
//                    Toast.makeText(applicationContext, "" + user.uid, Toast.LENGTH_SHORT).show()

                    if (!document.exists()) {
                    val intent = Intent(this@NavigationActivity, VehicleInfoActivity::class.java)
                    startActivity(intent)
                    finish()
                    }
                }
        }
        setContentView(R.layout.activity_navigation)

        if(NavigationActivity.fromVehiclePage || NavigationActivity.fromContactPage  || fromEditProfile || fromDrivewayPage){
            fm.beginTransaction().add(R.id.container, fragment22, "2").hide(fragment22).commit()
            fm.beginTransaction().add(R.id.container, fragment11, "1").hide(fragment11).commit()
            fm.beginTransaction().add(R.id.container, fragment33, "3").commit()
            active = fragment33
            navigation.selectedItemId = R.id.navigation_settings
            NavigationActivity.fromVehiclePage = false
            NavigationActivity.fromContactPage = false
            NavigationActivity.fromEditProfile = false
        }
        else{
            fm.beginTransaction().add(R.id.container, fragment33, "3").hide(fragment33).commit()
            fm.beginTransaction().add(R.id.container, fragment22, "2").hide(fragment22).commit()
            fm.beginTransaction().add(R.id.container, fragment11, "1").commit()
        }

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
