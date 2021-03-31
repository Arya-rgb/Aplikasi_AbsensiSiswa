package com.project.aplikasiabsensi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.project.aplikasiabsensi.R
import com.project.aplikasiabsensi.databinding.ActivityProfilBinding

class ProfilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        binding.namaSiswa.text = pref.getString("nama_akun", null)
        binding.kelasSiswa.text = pref.getString("kelas_mapel", null)
        binding.roleAkun.text = pref.getString("role", null)
        binding.idAkun.text = pref.getString("id_akun", null)

    }
}