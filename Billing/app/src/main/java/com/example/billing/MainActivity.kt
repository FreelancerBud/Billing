package com.example.billing

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import java.io.IOException
import java.util.*

class MainActivity : ComponentActivity() {
    private lateinit var emailInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var loginButton: Button

    private lateinit var credentials: Properties

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailInput = findViewById(R.id.emailInput)
        passwordInput = findViewById(R.id.passwordInput)
        loginButton = findViewById(R.id.loginButton)

        // Load credentials from properties file
        credentials = loadCredentials()

        loginButton.setOnClickListener {
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            if (true) {
                navigateUser(email)
            } else {
                Toast.makeText(this, "Invalid credentials!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadCredentials(): Properties {
        val properties = Properties()
        try {
            val inputStream = assets.open("credentials.properties")
            properties.load(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return properties
    }

    private fun authenticate(email: String, password: String): Boolean {
        val storedPassword = credentials.getProperty(email)
        return storedPassword == password
    }

    private fun navigateUser(email: String) {
        if (email == "admin") {
            startActivity(Intent(this, AdminPanelActivity::class.java))
        } else {
            startActivity(Intent(this, BillingActivity::class.java))
        }
        finish()
    }
}
