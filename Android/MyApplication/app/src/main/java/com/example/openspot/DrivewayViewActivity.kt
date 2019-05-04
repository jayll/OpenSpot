package com.example.openspot

import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.AdapterViewAnimator


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

//        val mItems = ArrayList<String>(30)
//        for (i in 0..29) {
//            mItems.add(String.format("Card number %2d", i))
//        }
//
//        val mAdapter = RecyclerView.Adapter<CardViewAdapter>
//
//        val mRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
//
//        mRecyclerView.layoutManager = LinearLayoutManager(this)
//        mRecyclerView.adapter = mAdapter
//
//        val swipeTouchListener = SwipeableRecyclerViewTouchListener(mRecyclerView,
//            object : SwipeableRecyclerViewTouchListener.SwipeListener {
//                fun canSwipe(position: Int): Boolean {
//                    return true
//                }
//
//                override fun onDismissedBySwipeLeft(recyclerView: RecyclerView, reverseSortedPositions: IntArray) {
//                    for (position in reverseSortedPositions) {
//                        mItems.removeAt(position)
//                        mAdapter.notifyItemRemoved(position)
//                    }
//                    mAdapter.notifyDataSetChanged()
//                }
//
//                override fun onDismissedBySwipeRight(recyclerView: RecyclerView, reverseSortedPositions: IntArray) {
//                    for (position in reverseSortedPositions) {
//                        mItems.removeAt(position)
//                        mAdapter.notifyItemRemoved(position)
//                    }
//                    mAdapter.notifyDataSetChanged()
//                }
//            })
//
//        mRecyclerView.addOnItemTouchListener(swipeTouchListener)
//

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
