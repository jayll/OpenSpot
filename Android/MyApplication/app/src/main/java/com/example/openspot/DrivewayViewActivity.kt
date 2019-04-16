package com.example.openspot

import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

class DrivewayViewActivity : AppCompatActivity() {

    private val driveway : Fragment = DrivewayFragment()
    private val fm = supportFragmentManager.beginTransaction()

    companion object {
        const val TAG = "DrivewayViewActivity"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_vehicle_view)

        fm.replace(R.id.pref_container, driveway,"drivewayFragment").commit()

    }

}
