package com.example.openspot

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class DrivewayFragment : PreferenceFragmentCompat() {
    private val db = FirebaseFirestore.getInstance()

    override fun onCreatePreferences(savedInstanceState: Bundle?, root_key: String?) {
        setPreferencesFromResource(R.xml.driveway_preferences, root_key)
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        preferenceScreen = this.preferenceScreen

        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        var counter = 0
        var drivewayArray: Any?


//        Toast.makeText(activity!!.baseContext, "" + currentFirebaseUser?.uid, Toast.LENGTH_SHORT).show()
        val docRef = db.collection("Users").document(currentFirebaseUser!!.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    drivewayArray = document.data!!["Addresses"]
                    Log.d(VehicleViewActivity.TAG, "DocumentSnapshot dataaaa: " + drivewayArray)

                    val a = drivewayArray as ArrayList<String>
                    var title = ""
                    for (i in a.indices) {
                        val preference = Preference(preferenceScreen.context)

                        when (i % 5) {
                            0 -> { //Address
                                title = a[i]
                            }
                            3 -> {//
                                title = title + "\n" + a[i] +":"
                            }
                            4 -> {//LicensePlate
                                preference.title = title + " " + a[i]
                                preference.key = "Driveway$counter"
                                preference.icon =
                                    ContextCompat.getDrawable(activity!!.baseContext, R.drawable.icons8_car_35)
                                preferenceScreen.addPreference(preference)
                                val drivewayButton = findPreference("Driveway$counter")
                                drivewayButton.setOnPreferenceClickListener {
                                    AuthUI.getInstance()
                                    val intent = Intent(activity!!.baseContext, ListDrivewayActivity::class.java)
                                    intent.putExtra("Address", a[i - 1])
                                    intent.putExtra("Latitude", a[i - 2])
                                    intent.putExtra("Longitude", a[i - 3])
                                    intent.putExtra("Active", a[i - 4])
                                    intent.putExtra("Price", a[i])
                                    intent.putExtra("counter", drivewayButton.key)
                                    startActivity(intent)
                                    true
                                }
                                counter++
                            }
                        }
                    }
                } else {
                    Log.d(VehicleViewActivity.TAG, "No such document")
                }
            }
    }
}