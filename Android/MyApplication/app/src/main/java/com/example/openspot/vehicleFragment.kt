package com.example.openspot

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.os.Build




class VehicleFragment : PreferenceFragmentCompat() {
    private val db = FirebaseFirestore.getInstance()

    override fun onCreatePreferences(savedInstanceState: Bundle?, root_key: String?) {
        setPreferencesFromResource(R.xml.vehicle_preferences, root_key)
        activity!!.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        preferenceScreen = this.preferenceScreen

        val currentFirebaseUser = FirebaseAuth.getInstance().currentUser
        var counter = 0
        var carArray :Any?


//        Toast.makeText(activity!!.baseContext, "" + currentFirebaseUser?.uid, Toast.LENGTH_SHORT).show()
        val docRef = db.collection("Users").document(currentFirebaseUser!!.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    carArray = document.data!!["Cars"]
                    Log.d(VehicleViewActivity.TAG, "DocumentSnapshot dataaaa: " + carArray)

                    val a  = carArray as ArrayList<String>
                    var title = ""
                    for(i in a.indices){
                        val preference = Preference(preferenceScreen.context)

                        when(i%5){
                            0 ->{ //CarMake
                                title = a[i]
                            }
                            1 ->{//CarModel
                                title = title + " " + a[i]
                            }
                            4->{//LicensePlate
                                preference.title = title + "\n"+ a[i]
                                preference.key = "Car$counter"
                                preference.icon = ContextCompat.getDrawable(activity!!.baseContext,R.drawable.icons8_car_35)
                                preferenceScreen.addPreference(preference)
                                val carButton = findPreference("Car$counter")
                                carButton.setOnPreferenceClickListener{
                                    AuthUI.getInstance()
                                    val intent = Intent(activity!!.baseContext, EditVehicleInfoActivity::class.java)
                                    intent.putExtra("carState", a[i-1])
                                    intent.putExtra("carColor", a[i-2])
                                    intent.putExtra("carModel", a[i-3])
                                    intent.putExtra("carMake", a[i-4])
                                    intent.putExtra("carLicense", a[i])
                                    intent.putExtra("counter",carButton.key)
                                    startActivity(intent)
                                    true
                                }
                                counter++
                            }
                        }
                    }
                }
                else {
                    Log.d(VehicleViewActivity.TAG, "No such document")
                }
            }
        }
    }