package com.example.laundryapp3469.controller // Sesuaikan dengan package controller Anda

import com.example.laundryapp3469.model.SetData
import com.example.laundryapp3469.model.Pemilik // Sesuaikan dengan lokasi model Pemilik Anda

class PemilikController {

    // 1. Method untuk menyimpan data login ke Shared Preferences
    fun setSharedPreferences(username: String, password: String) {
        //tambahkan data login ke Shared Preferences
        val editor = SetData.sharedPreferences.edit()
        editor.putString(SetData.USERNAME_KEY, username)
        editor.putString(SetData.PASSWORD_KEY, password)
        editor.apply()
    }

    // 2. Method untuk validasi login (cek username dan password)
    //method untuk validasi login (cek username dan password)
    fun login(username: String, password: String) : Boolean {
        //cek apakah username dan password ada di list dataPemilik
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
        //cek apakah email/no hp sudah terdaftar
        if (SetData.dataPemilik.any {
                it.email.equals(email, ignoreCase = true) || it.noHp == noHp
            }) {
            result = "Email atau No HP sudah terdaftar"
        } else {
            //tambahkan data ke list data Pemilik
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
            //tambahkan data register ke Shared Preferences
            setSharedPreferences(username = email, password)
            result = "Daftar Berhasil"
        }
        return result
    }
}