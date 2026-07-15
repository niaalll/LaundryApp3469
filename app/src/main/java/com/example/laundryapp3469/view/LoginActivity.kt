package com.example.laundryapp3469.view // Sesuaikan dengan package Anda jika berbeda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.laundryapp3469.R
import com.example.laundryapp3469.controller.PemilikController

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //declare object from front end
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<com.google.android.material.button.MaterialButton>(R.id.btnLogin)
        val btnLoginGoogle = findViewById<Button>(R.id.btnLoginGoogle)
        val tvRegisterPage = findViewById<TextView>(R.id.tvRegisterPage)
        val tvLupaPassword = findViewById<TextView>(R.id.tvLupaPassword)

        btnLogin.setOnClickListener {
            var username = etUsername.text.toString()
            var password = etPassword.text.toString()

            //create object PemilikController
            val loginController = PemilikController()

            //cek apakah email/noHp dan password tidak kosong
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Mohon isi username dan password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                //cek apakah username dan password ada di list dataPemilik
                if (loginController.login(username, password)) {
                    Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()

                    //intent explicit -> ke acvitity Home
                    val intentHome = Intent(this, HomeActivity::class.java)
                    startActivity(intentHome)
                } else {
                    //username atau password salah
                    Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}