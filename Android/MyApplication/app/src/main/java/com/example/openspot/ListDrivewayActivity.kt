package com.example.openspot

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import java.util.*
import android.widget.Switch
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class ListDrivewayActivity : AppCompatActivity() {
    companion object {
        const val TAG = "ListDrivewayActivity"
    }

    private val db = FirebaseFirestore.getInstance()
    private val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
    private var Address: String? = ""
    private var Latitude: Double = 0.0
    private var Longitude: Double = 0.0
    private var Price = 0
    private var toggle = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_list_driveway)

        autoComplete()

        val editPrice = findViewById<EditText>(R.id.editPrice)
        val priceBar = findViewById<SeekBar>(R.id.seekbar)

        editPrice.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                try {
                    //Update Seekbar value after entering a number
                    priceBar.progress = s.toString().toInt()
                    Price = s.toString().toInt()
                } catch (ex: Exception) {
                    Toast.makeText(applicationContext, "Input is invalid: $ex", Toast.LENGTH_SHORT).show()
                }
            }

        })

        priceBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                editPrice.setText("$progress")
            }

        })
        val onOffSwitch = findViewById<Switch>(R.id.on_off_switch)
        onOffSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            Log.v("Switch State=", "" + isChecked)
            if (isChecked) {
                toggle = 1
                onOffSwitch.text = "Active"
            } else {
                toggle = 0
                onOffSwitch.text = "Inactive"
            }
        }

    }

    private fun autoComplete() {
        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, "AIzaSyBuQ8aNzH2t7-bkfYUz2llMRTCfRAHvqzU")
        }
        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            this.supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG))
        autocompleteFragment.setHint("Search Driveway Address")
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {
                Log.d(TAG, "Place: " + p0.name + ", " + p0.id)
                Address = p0.name
                Latitude = p0.latLng!!.latitude
                Longitude = p0.latLng!!.longitude
            }

            override fun onError(p0: Status) {
                Log.d(TAG, "An error occured: $p0")
            }
        })
    }

    fun clickButton(v: View) {
        var drivewayArray: Any?
        var drivewayInfo: MutableList<String>

        val docRef = db.collection("Users").document(currentFirebaseUser!!.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    drivewayArray = document.data!!["Addresses"]
                    Log.d(VehicleViewActivity.TAG, "DocumentSnapshot dataaaa: " + drivewayArray.toString())
                    drivewayInfo = drivewayArray as MutableList<String>
                    drivewayInfo.add(Address.toString())
                    drivewayInfo.add(Latitude.toString())
                    drivewayInfo.add(Longitude.toString())
                    drivewayInfo.add(toggle.toString())
                    drivewayInfo.add(Price.toString())

                    db.collection("Users").document(currentFirebaseUser.uid)
                        .update("Addresses", drivewayInfo)
                        .addOnSuccessListener { documentReference ->
                            val i = Intent(this@ListDrivewayActivity, DrivewayViewActivity::class.java)
                            startActivity(i)
                        }
                        .addOnFailureListener { e ->
                            Log.w(VehicleInfoActivity.TAG, "Error adding document", e)
                        }
                }
            }
    }
}
