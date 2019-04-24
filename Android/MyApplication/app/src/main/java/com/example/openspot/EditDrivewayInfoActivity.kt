package com.example.openspot

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import java.util.*
import com.google.android.libraries.places.internal.db
import com.google.android.libraries.places.widget.Autocomplete
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_list_driveway.*


class EditDrivewayInfoActivity : AppCompatActivity() {
    companion object {
        const val TAG = "EditDrivewayInfoActivity"
        var fromEditDrivewayPage = false

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
        setContentView(R.layout.activity_edit_driveway_info)
        NavigationActivity.fromDrivewayPage = true
        autoComplete()

        val extras = intent.extras
        var AddressData :String? = extras!!.getString("Address")
        var LatitudeData:String? = extras!!.getString("Latitude")
        var LongitudeData:String? = extras!!.getString("Longitude")
        var ActiveData:String? = extras!!.getString("Active")
        var PriceData:String? = extras!!.getString("Price")
        var counterData:String? = extras!!.getString("counter2")


        val editPrice = findViewById<EditText>(R.id.editPrice)
        editPrice.setText(PriceData)
        val priceBar = findViewById<SeekBar>(R.id.seekbar)
        priceBar.progress = PriceData!!.toInt()

        editPrice.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                try {
                    //Update Seekbar value after entering a number
                    if(s!!.isEmpty()){
                        priceBar.progress = 0
                        Price = 0
                        editPrice.setSelection(editPrice.text.length)
                    }
                    else {
                        priceBar.progress = s.toString().toInt()
                        Price = s.toString().toInt()
                        editPrice.setSelection(editPrice.text.length)
                    }
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
        if(ActiveData == "1") {
            onOffSwitch.toggle()
            onOffSwitch.text = "Active"
        } else{
            onOffSwitch.text = "Inactive"
        }
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
        val extras = intent.extras
        var LatitudeData:String? = extras!!.getString("Latitude")
        var LongitudeData:String? = extras!!.getString("Longitude")
        var AddressData :String = extras!!.getString("Address")

//        Places.initialize(applicationContext, "AIzaSyDtZ0Q1iHHbGLPMj1hJMxo9Cig5jgLw38A")
        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, "AIzaSyDi9cU1TQ3J-M2sEty4HteQq_ttIaLrJMU")
        }
        // Initialize the AutocompleteSupportFragment.
        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG))
        autocompleteFragment.setHint("Search Driveway Address")
        autocompleteFragment.setText(AddressData)
        Address = AddressData
        Latitude = LatitudeData!!.toDouble()
        Longitude = LongitudeData!!.toDouble()
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(p0: Place) {
//                Log.d(TAG, "Place: " + p0.name + ", " + p0.id)
                Address = p0.name
                Latitude = p0.latLng!!.latitude
                Longitude = p0.latLng!!.longitude
            }

            override fun onError(p0: Status) {
                Log.d(TAG, "An error occured: $p0")
            }
        })
    }

    private fun saveDrivewayInfo(v: View) {
        val extras = intent.extras
        val editText = findViewById<EditText>(R.id.editPrice)
        val switch = findViewById<Switch>(R.id.on_off_switch)
        val value = editText.text.toString()
        val value2 = switch.text.toString()
        var name =""
        var counterData:String? = extras!!.getString("counter2")
        var drivewayNumber = counterData.toString()[counterData.toString().length-1].toInt()
        var drivewayArray: Any?
        var drivewayInfo: MutableList<String>

        val docRef = db.collection("Users").document(currentFirebaseUser!!.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    drivewayArray = document.data!!["Addresses"]
                    Log.d(VehicleViewActivity.TAG, "DocumentSnapshot dataaaa: " + drivewayArray.toString())
                    drivewayInfo = drivewayArray as MutableList<String>
//                    drivewayInfo.add(Address.toString())
//                    drivewayInfo.add(Latitude.toString())
//                    drivewayInfo.add(Longitude.toString())
//                    drivewayInfo.add(toggle.toString())
//                    drivewayInfo.add(Price.toString())
                    for(i in 0..4) {
                        when (i) {
                            0 -> {
                                drivewayInfo[((drivewayNumber-48) * 5) + i] = Address.toString()

                            }
                            1 -> {
                                drivewayInfo[((drivewayNumber-48) * 5) + i] = Latitude.toString()

                            }
                            2 -> {
                                drivewayInfo[((drivewayNumber-48) * 5) + i] = Longitude.toString()

                            }
                            3 -> {
                                drivewayInfo[((drivewayNumber-48) * 5) + i] = value
                                name += "\n" + value
                                Preference(this).title = name
                            }
                            4 ->
                            {
                                if (switch.text.toString() == "Active"){
                                    drivewayInfo[((drivewayNumber-48) * 5) + i] = "1"
                                    name += "\n" + "1"
                                }
                                else{
                                    drivewayInfo[((drivewayNumber-48) * 5) + i] = "0"
                                    name += "\n" + "0"
                                }
                            }
                        }
                    }

                    db.collection("Users").document(currentFirebaseUser.uid)
                        .update("Addresses", drivewayInfo)
                        .addOnSuccessListener { documentReference ->
                            val i = Intent(this@EditDrivewayInfoActivity, DrivewayViewActivity::class.java)
                            startActivity(i)
                        }
                        .addOnFailureListener { e ->
                            Log.w(VehicleInfoActivity.TAG, "Error adding document", e)
                        }
                }
            }
    }

    private fun deleteDrivewayInfo(v:View) {
        val extras = intent.extras
        var drivewayArray: Any?
        var counterData:String? = extras!!.getString("counter2")
        var drivewayNumber = counterData.toString()[counterData.toString().length-1].toInt()
        var drivewayInfo: MutableList<String>

        val docRef = db.collection("Users").document(currentFirebaseUser!!.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    drivewayArray = document.data!!["Addresses"]
                    Log.d(VehicleViewActivity.TAG, "DocumentSnapshot dataaaa: " + drivewayArray.toString())
                    drivewayInfo = drivewayArray as MutableList<String>
                    for(i in 0..4){
                        drivewayInfo.removeAt((drivewayNumber - 48) * 5)
                    }

                    db.collection("Users").document(currentFirebaseUser.uid)
                        .update("Addresses", drivewayInfo)
                        .addOnSuccessListener { documentReference ->
                            val i = Intent(this@EditDrivewayInfoActivity, DrivewayViewActivity::class.java)
                            startActivity(i)
                        }
                        .addOnFailureListener { e ->
                            Log.w(VehicleInfoActivity.TAG, "Error adding document", e)
                        }
                }
            }
    }

    fun clickButton(v:View){
        EditDrivewayInfoActivity.fromEditDrivewayPage = true
        saveDrivewayInfo(v)
    }


    fun deleteButton(v:View){
        deleteDrivewayInfo(v)
    }

}
