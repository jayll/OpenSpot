package com.example.openspot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_edit_profile.*


class EditProfile : AppCompatActivity() {
    val TAG = "EditProfile"
    //Firebase for User Profile title replacement with Users Full name
    private val db = FirebaseFirestore.getInstance()
    private val currentFirebaseUser = FirebaseAuth.getInstance().currentUser


    private var FullName: TextView? = null
    private var birthday: TextView? = null
    private var nextButton: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.FragmentTheme)
        setContentView(R.layout.activity_edit_profile)

        supportActionBar?.title = "Edit Profile"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        NavigationActivity.fromEditProfile = true

        initialize()

    }

    private fun initialize() {
        var editEmail: EditText?
        FullName = findViewById<View>(R.id.fullNameView) as TextView
        //update email
        editEmail = findViewById<View>(R.id.emailUpdate) as EditText
        birthday = findViewById<View>(R.id.birthdayView) as TextView
        val phoneNumber = findViewById<View>(R.id.phoneNumberView) as TextView
        phoneNumber.text = currentFirebaseUser!!.phoneNumber
        nextButton = findViewById<View>(R.id.checkmark) as ImageButton

        val docRef = db.collection("Users").document(currentFirebaseUser!!.uid)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document!=null){
                    FullName?.text = document.data!!["fullName"].toString()
                    birthday?.text = document.data!!["dateOfBirth"].toString()
                    editEmail.setText(document.data!!["email"].toString())
                    editEmail.setSelection(document.data!!["email"].toString().length)
                }}

    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validForm(): Boolean {
        var editEmail: EditText?
        editEmail = findViewById<View>(R.id.emailUpdate) as EditText
        if (!isValidEmail(editEmail!!.text.toString())) {
            Toast.makeText(applicationContext, "Please enter valid Email", Toast.LENGTH_LONG)
                .show()
            return false
        } else {
            return true
        }
    }

    fun clickButton(v:View) {
        var editEmail: EditText? = findViewById(R.id.emailUpdate)
        val value = editEmail!!.text.toString()
        if (validForm()) {
            db.collection("Users").document(currentFirebaseUser!!.uid)
                .update("email", value)
                .addOnSuccessListener {
                    Log.d(VehicleInfoActivity.TAG, "DocumentSnapshot added with ID: Saved boi")
                    Toast.makeText(applicationContext, "Email Saved", Toast.LENGTH_LONG)
                        .show()
                    val i = Intent(this@EditProfile, NavigationActivity::class.java)
                    startActivity(i)
                    finish()
                }
        }
    }


}
