package com.example.laundryapp3469.model // Sesuaikan dengan package model Anda

import android.content.SharedPreferences

object SetData {
    // Variabel dan konstanta untuk SharedPreferences
    lateinit var sharedPreferences: SharedPreferences
    const val SHARED_PREFS = "sharedPrefs"
    const val USERNAME_KEY = "usernameKey"
    const val PASSWORD_KEY = "passwordKey"

    // List data pemilik laundry
    var dataPemilik: MutableList<Pemilik> = mutableListOf(
        //data pemilik 1
        Pemilik(
            namaLengkap = "Stevi Ema Wijayanti",
            email = "stevi.ema@amikom.ac.id",
            noHp = "085743439096",
            password = "stevi123",
            namaLaundry = "Laundry Stevi",
            cabangLaundry = ""
        ),
        //data pemilik 2
        Pemilik(
            namaLengkap = "Dibyo",
            email = "dibyo@gmail.com",
            noHp = "085325005200",
            password = "12345",
            namaLaundry = "Star Laundry",
            cabangLaundry = ""
        )
    )
}