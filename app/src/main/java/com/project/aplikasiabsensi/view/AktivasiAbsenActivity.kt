package com.project.aplikasiabsensi.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.project.aplikasiabsensi.databinding.ActivityAktivasiAbsenBinding
import com.project.aplikasiabsensi.model.AbsensiRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class AktivasiAbsenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAktivasiAbsenBinding

    companion object {
        const val EXTRA_IDMAPEL = "EXTRA_IDMAPEL"
        const val EXTRA_NAMA_MAPEL = "EXTRA_NAMA_MAPEL"
        const val EXTRA_HARI_MAPEL = "EXTRA_HARI_MAPEL"
        const val EXTRA_JAM_MAPEL = "EXTRA_JAM_MAPEL"
        const val EXTRA_KELAS_MAPEL = "EXTRA_KELAS_MAPEL"
        const val EXTRA_NAMA_GURU = "EXTRA_NAMA_GURU"
        const val EXTRA_SEMESTER = "EXTRA_SEMESTER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAktivasiAbsenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rnds = (100000 until 999999).random()

        binding.apply {
            intextIdmapel.text = intent.getStringExtra(EXTRA_IDMAPEL)
            intextNamamapel.text = intent.getStringExtra(EXTRA_NAMA_MAPEL)
            intextHarimapel.text = intent.getStringExtra(EXTRA_HARI_MAPEL)
            intextJammapel.text = intent.getStringExtra(EXTRA_JAM_MAPEL)
            intextKelasmapel.text = intent.getStringExtra(EXTRA_KELAS_MAPEL)
            intextNamaguru.text = intent.getStringExtra(EXTRA_NAMA_GURU)
            intextSemester.text = intent.getStringExtra(EXTRA_SEMESTER)
            textPinabsen.text = rnds.toString()
        }

        binding.btnAktifFormAbsen.setOnClickListener {
            binding.progressAktivasi.visibility = View.VISIBLE
            val responseListener = Response.Listener<String> { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    val success = jsonResponse.getBoolean("success")
                    if (success) {
                        binding.progressAktivasi.visibility = View.INVISIBLE
                        val builder = AlertDialog.Builder(this@AktivasiAbsenActivity)
                        builder.setMessage("Absen Telah Di Aktifkaan")
                            .setNegativeButton("OK", null)
                            .create()
                            .show()
                    } else {
                        binding.progressAktivasi.visibility = View.INVISIBLE
                        val builder = AlertDialog.Builder(this@AktivasiAbsenActivity)
                        builder.setMessage("Gagal Terhubung Ke Server")
                            .setNegativeButton("Silahkan Cek Koneksi Internet Anda", null)
                            .create()
                            .show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            val absensiRequest = AbsensiRequest(
                binding.intextIdmapel.text.toString(),
                binding.intextHarimapel.text.toString(),
                binding.intextJammapel.text.toString(),
                binding.intextKelasmapel.text.toString(),
                binding.intextNamaguru.text.toString(),
                binding.intextSemester.text.toString(),
                binding.textPinabsen.text.toString(),
                binding.intextNamamapel.text.toString() ,responseListener
            )
            val queue = Volley.newRequestQueue(this@AktivasiAbsenActivity)
            queue.add(absensiRequest)
        }
        }
    }
