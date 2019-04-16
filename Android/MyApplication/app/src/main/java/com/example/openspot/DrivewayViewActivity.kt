package com.example.openspot

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem


class DrivewayViewActivity : AppCompatActivity() {

    private val driveway : Fragment = DrivewayFragment()
    private val fm = supportFragmentManager.beginTransaction()

    companion object {
        const val TAG = "DrivewayViewActivity"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setTheme(R.style.FragmentTheme)
        supportActionBar?.title = "Driveway"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_driveway_view)
        NavigationActivity.fromDrivewayPage = true
        fm.replace(R.id.drivewayContainer, driveway,"drivewayFragment").commit()
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
            val intent = Intent(this@DrivewayViewActivity, ListDrivewayActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
