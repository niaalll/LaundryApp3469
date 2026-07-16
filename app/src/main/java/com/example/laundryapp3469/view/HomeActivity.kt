package com.example.laundryapp3469.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment // Pastikan menggunakan import ini
import com.example.laundryapp3469.R
import com.example.laundryapp3469.controller.PemilikController

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

        //get data pemilik dari Shared Preferences
        val controller = PemilikController()
        val pemilik = controller.getDataPemilik()

        //set data pemilik ke tampilan
        val tvNamaLaundry = findViewById<TextView>(R.id.tvNamaLaundry)
        val tvNamaUser = findViewById<TextView>(R.id.tvNamaUser)
        if (pemilik != null) {
            tvNamaLaundry.text = pemilik.namaLaundry
            tvNamaUser.text = pemilik.namaLengkap
        }

        //pop up menu
        val imvDropDownUser = findViewById<ImageView>(R.id.imvDropDownUser)
        imvDropDownUser.setOnClickListener {
            val popupMenu = PopupMenu(this, imvDropDownUser)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menuAccount -> {
                        Toast.makeText(this, "Account", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.menuLogout -> {
                        val pemilikController = PemilikController()
                        pemilikController.setSharedPreferences("", "")
                        val intentLogin = Intent(this, LoginActivity::class.java)
                        startActivity(intentLogin)
                        finish()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

        //load Home Fragment ketika Home Activity dipanggil
        loadFragment(HomeFragment())

        val navBarHome = findViewById<Button>(R.id.navBarHome)
        // DIBERSIHKAN: Diganti dari navBarOrders ke navBarOrder agar cocok dengan ID XML
        val navBarOrder = findViewById<Button>(R.id.navBarOrder)
        val navBarReport = findViewById<Button>(R.id.navBarReport)
        val navBarTracking = findViewById<Button>(R.id.navBarTracking)

        //set perpindahan fragment
        navBarHome.setOnClickListener {
            loadFragment(HomeFragment())
        }
        navBarOrder.setOnClickListener {
            loadFragment(OrderFragment())
        }
        navBarReport.setOnClickListener {
            loadFragment(ReportFragment())
        }
        navBarTracking.setOnClickListener {
            loadFragment(TrackingFragment())
        }
    }

    //fungsi untuk load fragment - perpindahan fragment
    private fun loadFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fmlHome, fragment)
        fragmentTransaction.commit()
    }
}