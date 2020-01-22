package com.harshithd.firebaseauth

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_profile.*
import java.lang.Exception
import java.util.concurrent.TimeUnit

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        FirebaseAuth.getInstance().currentUser?.let { firebaseUser ->
            // if the user is logged in, display their info on the screen
            etEmail.setText(firebaseUser.email)
            etName.setText(firebaseUser.displayName)
            etPhone.setText(firebaseUser.phoneNumber)
            Picasso.get().load(firebaseUser.photoUrl).into(ivUserImage)
        }

        btnUpdateImage.setOnClickListener {
            // update the profile image here
            val picUpdate = UserProfileChangeRequest.Builder()
                .setPhotoUri(Uri.parse("new.photo.url.here"))
                .build()

            FirebaseAuth.getInstance().currentUser?.updateProfile(picUpdate)
        }

        btnUpdateName.setOnClickListener {
            // update the name here
            val nameUpdate = UserProfileChangeRequest.Builder()
                .setDisplayName(etName.text.toString())
                .build()

            FirebaseAuth.getInstance().currentUser?.updateProfile(nameUpdate)
        }

        btnUpdateEmail.setOnClickListener {
            // update the email here
            FirebaseAuth.getInstance().currentUser?.updateEmail(etEmail.text.toString())

        }

        btnUpdatePhone.setOnClickListener {
            // update the phone number here
            // https://firebase.google.com/docs/auth/android/phone-auth
        }

        btnUpdatePwd.setOnClickListener {
            // update the password here
            FirebaseAuth.getInstance().currentUser?.updatePassword(etPassword.text.toString())
        }

    }

}