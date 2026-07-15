package com.example.laundryapp3469.view // Sesuaikan dengan package Anda jika berbeda

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.laundryapp3469.R
import com.example.laundryapp3469.controller.PemilikController

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //declare object from front end
        val etNamaLengkap = findViewById<EditText>(R.id.etNamaLengkap)
        val etNoHp = findViewById<EditText>(R.id.etNoHp)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etKonfirmasiPassword = findViewById<EditText>(R.id.etKonfirmasiPassword)
        val etNamaLaundry = findViewById<EditText>(R.id.etNamaLaundry)
        val etCabangLaundry = findViewById<EditText>(R.id.etCabangLaundry)
        val cbSyaratKetentuan = findViewById<CheckBox>(R.id.cbSyaratKetentuan)
        val btnDaftar = findViewById<com.google.android.material.button.MaterialButton>(R.id.btnDaftar)
        val tvLoginPage = findViewById<TextView>(R.id.tvLoginPage)
        val tvSyaratKetentuan = findViewById<TextView>(R.id.tvSyaratKetentuan)
        val tvKebijakanPrivasi = findViewById<TextView>(R.id.tvkebiajakn)

        //event pindah halaman ke login page (Intent Explicit)
        tvLoginPage.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnDaftar.setOnClickListener {
            var namaLengkap = etNamaLengkap.text.toString()
            var noHp = etNoHp.text.toString()
            var email = etEmail.text.toString()
            var password = etPassword.text.toString()
            var konfirmasiPassword = etKonfirmasiPassword.text.toString()
            var namaLaundry = etNamaLaundry.text.toString()
            var cabangLaundry = etCabangLaundry.text.toString()

            //pastikan data tidak kosong
            if (namaLengkap.isEmpty() || noHp.isEmpty() || email.isEmpty() ||
                password.isEmpty() || konfirmasiPassword.isEmpty() ||
                namaLaundry.isEmpty()) {
                Toast.makeText(this, "Mohon isi data lengkap", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //jika password & konfirmasi password tidak sama
            if (password != konfirmasiPassword) {
                Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //jika checkbox tidak ter-checklist
            if (!cbSyaratKetentuan.isChecked) {
                Toast.makeText(this, "Mohon menyetujui syarat & ketentuan", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //jika data cabang kosong/tidak diisi
            if (cabangLaundry.isEmpty()) {
                cabangLaundry = "Tidak ada"
            }

            //create object PemilikController
            val pemilikController = PemilikController()

            //register
            val result = pemilikController.register(
                namaLengkap,
                noHp,
                email,
                password,
                namaLaundry,
                cabangLaundry
            )
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()

            // Jika pendaftaran berhasil, arahkan ke HomeActivity
            if (result == "Daftar Berhasil") {
                //intent explicit -> ke acvitity Home
                val intentHome = Intent(this, HomeActivity::class.java)
                startActivity(intentHome)
                finish() // Menutup halaman register agar tidak menumpuk di backstack
            }
        }
    }
}