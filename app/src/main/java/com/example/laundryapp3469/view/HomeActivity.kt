package com.example.laundryapp3469.view // Sesuaikan dengan package Anda

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.laundryapp3469.R
import com.example.laundryapp3469.view.WelcomeActivity.Companion.PASSWORD_KEY
import com.example.laundryapp3469.view.WelcomeActivity.Companion.USERNAME_KEY

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnLogOut = findViewById<com.google.android.material.button.MaterialButton>(R.id.btnLogOut)
        btnLogOut.setOnClickListener {
            //kosongkan data login ke Shared Preferences
            val editor = WelcomeActivity.sharedPreferences.edit()
            editor.putString(USERNAME_KEY, null)
            editor.putString(PASSWORD_KEY, null)
            editor.apply()

            //intent explicit untuk memanggil Login Page
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
    }
}