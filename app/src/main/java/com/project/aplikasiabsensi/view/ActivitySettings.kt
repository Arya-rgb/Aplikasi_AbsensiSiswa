package com.project.aplikasiabsensi.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.project.aplikasiabsensi.databinding.ActivitySettingsBinding

class ActivitySettings : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        binding.namaLogout.text = pref.getString("nama_akun", null)

        binding.btnLogout.setOnClickListener {
            logOutVerification()
        }


    }

    private fun logOutVerification() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Apakah anda yakin ?")
        builder.setMessage(
            """
            Anda akan ke halaman login setelah logout !
        """.trimIndent()
        )
        builder.setPositiveButton("Ya") { _, _ ->
            clearAppData()
        }
        builder.setNegativeButton("Tidak") { dialog, _ -> // Do nothing
            dialog.dismiss()
        }
        val alert = builder.create()
        alert.show()
    }

    private fun clearAppData() {
        val pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        val sp = applicationContext.getSharedPreferences("login", MODE_PRIVATE)
        pref.edit().clear().apply()
        sp.edit().clear().apply()

        val intent = Intent(this@ActivitySettings, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}