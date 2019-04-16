package com.example.openspot

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val user = FirebaseAuth.getInstance().currentUser
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onCreate(savedInstanceState)
        setTheme(R.style.FragmentTheme)
        setContentView(R.layout.activity_contact)

        supportActionBar?.title = "Contact"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        NavigationActivity.fromContactPage = true

        val yourSubject = findViewById<EditText>(R.id.your_subject)
        val yourMessage = findViewById<EditText>(R.id.your_message)


        val button = findViewById<Button>(R.id.post_message)
        button.setOnClickListener{
            val subject = yourSubject.text.toString()
            val message = yourMessage.text.toString()

            if(TextUtils.isEmpty(subject)){
                your_subject.error = "Enter Your Subject Line"
                your_subject.requestFocus()
            }

            if(TextUtils.isEmpty(message)){
                your_message.error = "Enter Your Message"
                your_message.requestFocus()
            }

            val sendEmail = Intent(android.content.Intent.ACTION_SENDTO)
            val data = "\n\n\n\n------------------------------------------------ \n Please enter" +
                    " your questions/comments above this line" + "\n\n UID: " + user!!.uid + "\n\n MetaData: " +
                    user.metadata

            sendEmail.type = "message/rfc822"
            sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, subject)
            sendEmail.putExtra(android.content.Intent.EXTRA_TEXT, message + data)
            sendEmail.data = Uri.parse("mailto:csecse442+support@gmail.com")

            if(!subject.isEmpty() && !message.isEmpty()) {
                startActivity(Intent.createChooser(sendEmail, "Send mail..."))
            }
        }
    }


}
