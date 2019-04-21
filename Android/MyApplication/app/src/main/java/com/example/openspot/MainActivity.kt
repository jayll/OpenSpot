package com.example.openspot

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private companion object {
        const val RC_SIGN_IN = 1
    }

    private val firebaseAuth: FirebaseAuth? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        val providers = arrayListOf(
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        // Create and launch sign-in intent
        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setLogo(R.drawable.hello)
                .setIsSmartLockEnabled(false)
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val intent = Intent(this@MainActivity, userProfile::class.java)
                startActivity(intent)
                finish()
            }
            // ...
        } else {
            Log.i("Error Code: ", resultCode.toString())
            Log.i("Error Code: ", requestCode.toString())
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }


    override fun onStart() {
        super.onStart()
        firebaseAuth?.addAuthStateListener(mAuthListener!!)
    }


    override fun onResume() {
        super.onResume()
        firebaseAuth?.addAuthStateListener(mAuthListener!!)
    }


    override fun onStop() {
        super.onStop()
        firebaseAuth?.removeAuthStateListener(mAuthListener!!)
    }
}







