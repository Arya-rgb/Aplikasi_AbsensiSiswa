package com.project.aplikasiabsensi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.aplikasiabsensi.R
import com.project.aplikasiabsensi.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //calling shared preferences
        val pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)

        //load string from shared preferences
        binding.nameHeader.text = pref.getString("nama_akun", null)

        binding.menuProfil.setOnClickListener {
            val intent = Intent (this@DashboardActivity, ProfilActivity::class.java)
            startActivity(intent)
        }

        binding.qrCode.setOnClickListener {
            val intent = Intent (this@DashboardActivity, QrCodeActivity::class.java)
            startActivity(intent)
        }

        binding.pelajaran.setOnClickListener {
            val intent = Intent (this@DashboardActivity, PelajaranActivity::class.java)
            startActivity(intent)
        }

        binding.absenmenu.setOnClickListener {
            val intent = Intent (this@DashboardActivity, AbsensiActivity::class.java)
            startActivity(intent)
        }

        binding.pengaturan.setOnClickListener {
            val intent = Intent (this@DashboardActivity, ActivitySettings::class.java)
            startActivity(intent)
        }



    }
    //prevent user to go back to login activity
    override fun onBackPressed() {
        this@DashboardActivity.moveTaskToBack(true)
    }
}