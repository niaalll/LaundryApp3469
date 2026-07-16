package com.example.laundryapp3469.controller // Sesuaikan dengan package controller Anda

import com.example.laundryapp3469.model.SetData
import com.example.laundryapp3469.model.Pemilik // Sesuaikan dengan package model Anda

class PemilikController {

    // 1. Method untuk menyimpan data login ke Shared Preferences
    fun setSharedPreferences(username: String, password: String) {
        val editor = SetData.sharedPreferences.edit()
        editor.putString(SetData.USERNAME_KEY, username)
        editor.putString(SetData.PASSWORD_KEY, password)
        editor.apply()
    }

    // 2. Method untuk validasi login (cek username dan password)
    fun login(username: String, password: String) : Boolean {
        var loginStatus = SetData.dataPemilik.any {
            (it.email.equals(username, ignoreCase = true) || it.noHp == username) &&
                    it.password == password
        }
        return loginStatus
    }

    // 3. Method untuk register -> tambah list dataPemilik
    fun register(
        namaLengkap: String,
        noHp: String,
        email: String,
        password: String,
        namaLaundry: String,
        cabangLaundry: String
    ): String {
        var result = ""
        if (SetData.dataPemilik.any {
                it.email.equals(email, ignoreCase = true) || it.noHp == noHp
            }) {
            result = "Email atau No HP sudah terdaftar"
        } else {
            SetData.dataPemilik.add(
                Pemilik(
                    namaLengkap,
                    email,
                    noHp,
                    password,
                    namaLaundry,
                    cabangLaundry
                )
            )
            setSharedPreferences(username = email, password)
            result = "Daftar Berhasil"
        }
        return result
    }

    // 4. Method untuk mengambil data pemilik berdasarkan username dan password
    fun getDataPemilik() : Pemilik? {
        val username = SetData.sharedPreferences.getString(
            SetData.USERNAME_KEY,
            ""
        )

        val password = SetData.sharedPreferences.getString(
            SetData.PASSWORD_KEY,
            ""
        )

        return SetData.dataPemilik.find {
            (it.email.equals(username, ignoreCase = true)
                    || it.noHp == username)
                    && it.password == password
        }
    }
}