package com.example.laundryapp3469.view // Sesuaikan dengan package view Anda

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.laundryapp3469.R
import com.example.laundryapp3469.model.SetData

class WelcomeActivity : AppCompatActivity() {

    private var username: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //ambil data yang tersimpan di SharedPreferences
        SetData.sharedPreferences = getSharedPreferences(SetData.SHARED_PREFS, MODE_PRIVATE)
        username = SetData.sharedPreferences.getString(SetData.USERNAME_KEY, null)
        password = SetData.sharedPreferences.getString(SetData.PASSWORD_KEY, null)

        val btnDaftar = findViewById<com.google.android.material.button.MaterialButton>(R.id.btnRegristerPage)
        btnDaftar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val btnLoginPage = findViewById<com.google.android.material.button.MaterialButton>(R.id.btnLoginPage)
        //event pindah halaman ke login page (Intent Explicit)
        btnLoginPage.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        // Cek login otomatis: jika username dan password sudah tersimpan, langsung ke Home
        if (username != null && password != null) {
            val intentHome = Intent(this, HomeActivity::class.java)
            startActivity(intentHome)
            finish() // Menutup WelcomeActivity agar user tidak bisa 'back' ke halaman ini lagi
        }
    }
}