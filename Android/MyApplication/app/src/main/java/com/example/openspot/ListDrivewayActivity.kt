package com.example.openspot

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import com.firebase.ui.auth.util.ui.BucketedTextChangeListener
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import java.util.*
import com.google.android.libraries.places.internal.s






class ListDrivewayActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ListDrivewayActivity"
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_list_driveway)

        autoComplete()

        val editPrice = findViewById<EditText>(R.id.editPrice)
        val priceBar = findViewById<SeekBar>(R.id.seekbar)

        editPrice.addTextChangedListener(object: TextWatcher{
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                try {
                    //Update Seekbar value after entering a number
                    priceBar.progress = s.toString().toInt()
                } catch (ex: Exception) {
                    Toast.makeText(applicationContext, "Input is invalid: $ex", Toast.LENGTH_SHORT).show()
                }
            }

        })

        priceBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                editPrice.setText("$progress")
            }

        })


    }
    private fun autoComplete(){
        Places.initialize(applicationContext, "AIzaSyDtZ0Q1iHHbGLPMj1hJMxo9Cig5jgLw38A")
        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, "AIzaSyBuQ8aNzH2t7-bkfYUz2llMRTCfRAHvqzU")
        }
        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME))

        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {
                Log.d(TAG, "Place: " + p0.name + ", " + p0.id)

            }
            override fun onError(p0: Status) {
                Log.d(TAG, "An error occured: $p0")
            }
        })
    }
}
