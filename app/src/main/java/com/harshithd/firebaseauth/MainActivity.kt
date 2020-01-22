package com.harshithd.firebaseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseAuth.getInstance().addAuthStateListener { firebaseAuth ->
            // called once this listener is attached and every time after the auth state changes

            firebaseAuth.currentUser?.let {
                // the user is logged in
                startActivity(Intent(this, ProfileActivity::class.java))
            } ?: run {
                // the user is logged out, log him/her in
                signIn()
            }
        }

    }

    private fun signIn() {
        // we are using Google, Email-Password, and Phone Number based authentication
        val providers = listOf(
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        val authIntent = AuthUI.getInstance().createSignInIntentBuilder()
            // set a custom logo to be shown on the login screen
            .setLogo(R.mipmap.ic_launcher)
            // set the login screen's theme
            .setTheme(R.style.AppThemeNoActionbar)
            // define the providers that will be used
            .setAvailableProviders(providers)
            // disable smart lock that automatically logs in a previously logged in user
            .setIsSmartLockEnabled(false)
            // set the terms of service and private policy URL for your app
            .setTosAndPrivacyPolicyUrls("example.termsofservice.com", "example.privatepolicy.com")
            .build()

        startActivity(authIntent)

    }

    fun signOut() {
        AuthUI.getInstance().signOut(this)
    }

}
