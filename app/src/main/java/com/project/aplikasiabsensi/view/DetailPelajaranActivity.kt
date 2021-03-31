package com.project.aplikasiabsensi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.project.aplikasiabsensi.databinding.ActivityDetailPelajaranBinding
import com.project.aplikasiabsensi.model.PostResponse
import java.util.*
import java.util.Locale.ROOT

class DetailPelajaranActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var binding : ActivityDetailPelajaranBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPelajaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemData = intent.getParcelableExtra<PostResponse>(EXTRA_DETAIL) as PostResponse
        binding.apply {
            textIdMapel.text = itemData.id_mapel
            textNamaMapel.text = itemData.nama_mapel
            txtHariMapel.text = itemData.hari_mapel
            txtJamMapel.text = itemData.jam_mapel
            textKelasMapel.text = itemData.kelas_mapel
            textNamaGuru.text = itemData.nama_guru
            textBsemester.text = itemData.semester
        }

        val pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        val role = pref.getString("role", null)
        val kelasSiswa = pref.getString("kelas_mapel", null)

        if (role!!.toLowerCase(ROOT).contains("pengajar")) {
            binding.btnAktifAbsen.visibility = View.VISIBLE
        }

        if (kelasSiswa == itemData.kelas_mapel.toString()) {
            binding.textYeskelas.visibility = View.VISIBLE
        }

        binding.btnAktifAbsen.setOnClickListener {
            val moveintent = Intent(this@DetailPelajaranActivity, AktivasiAbsenActivity::class.java)
            moveintent.putExtra(AktivasiAbsenActivity.EXTRA_IDMAPEL, binding.textIdMapel.text.toString())
            moveintent.putExtra(AktivasiAbsenActivity.EXTRA_NAMA_MAPEL, binding.textNamaMapel.text.toString())
            moveintent.putExtra(AktivasiAbsenActivity.EXTRA_HARI_MAPEL, binding.txtHariMapel.text.toString())
            moveintent.putExtra(AktivasiAbsenActivity.EXTRA_JAM_MAPEL, binding.txtJamMapel.text.toString())
            moveintent.putExtra(AktivasiAbsenActivity.EXTRA_KELAS_MAPEL, binding.textKelasMapel.text.toString())
            moveintent.putExtra(AktivasiAbsenActivity.EXTRA_NAMA_GURU, binding.textNamaGuru.text.toString())
            moveintent.putExtra(AktivasiAbsenActivity.EXTRA_SEMESTER, binding.textBsemester.text.toString())
            startActivity(moveintent)
        }
    }
}