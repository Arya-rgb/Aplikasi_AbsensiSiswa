package com.project.aplikasiabsensi.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.project.aplikasiabsensi.R
import com.project.aplikasiabsensi.databinding.ActivityAbsenSiswaBinding
import com.project.aplikasiabsensi.model.*
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class AbsenSiswaActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var binding: ActivityAbsenSiswaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbsenSiswaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemData = intent.getParcelableExtra<AbsenResponse>(EXTRA_DETAIL) as AbsenResponse
        binding.apply {
            absnPinabsen.text = itemData.pin_absen
            absnHariMapel.text = itemData.hari_mapel
            absnJamMapel.text = itemData.waktu_mapel
            absnKelasMapel.text = itemData.kelas_mapel
            absnNamaGuru.text = itemData.nama_guru
            absnBsemester.text = itemData.semester
            absnNamaMapel.text = itemData.nama_mapel
            absnIdMapel.text = itemData.id_absen
        }

        val pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        val role = pref.getString("role", null)
        val kelasSiswa = pref.getString("kelas_mapel", null)

        if (role!!.toLowerCase(Locale.ROOT).contains("pengajar")) {
            binding.viewpin.visibility = View.VISIBLE
            binding.nonaktifButton.visibility = View.VISIBLE
        }

        if (kelasSiswa == itemData.kelas_mapel.toString()) {
            binding.absenButton.visibility = View.VISIBLE
            binding.contentabsen1.visibility = View.VISIBLE
            binding.inputPin.visibility = View.VISIBLE
        }


        binding.absenButton.setOnClickListener {
            if (binding.inputPin.text.toString() == itemData.pin_absen.toString()) {
                absenSiswa()
            } else {
                val builder = AlertDialog.Builder(this@AbsenSiswaActivity)
                builder.setMessage("Pin yang anda masukan salah")
                    .setNegativeButton("Ok", null)
                    .create()
                    .show()
            }
        }

        binding.nonaktifButton.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            val id_absen = itemData.id_absen
            val responseListener =
                Response.Listener<String> { response ->
                    try {
                        val jsonResponse = JSONObject(response)
                        val success = jsonResponse.getBoolean("success")
                        if (success) {
                            binding.progress.visibility = View.INVISIBLE
                            onSuccessDelete()
                        } else {
                            val builder = AlertDialog.Builder(this@AbsenSiswaActivity)
                            binding.progress.visibility = View.INVISIBLE
                            builder.setMessage("Tidak Dapat Menghaapus")
                                .setNegativeButton("Ok", null)
                                .create()
                                .show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            val deleteRequest = DeleteRequest(id_absen, responseListener)
            val queue = Volley.newRequestQueue(this@AbsenSiswaActivity)
            queue.add(deleteRequest)

        }



    }

    private fun onSuccessDelete() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Sukses")
        builder.setMessage(
            """
            Terimakasih, absen telah di nonaktifkan
        """.trimIndent()
        )
        builder.setNegativeButton("OK") { dialog, _ -> // Do nothing
            val intent = Intent (this@AbsenSiswaActivity, DashboardActivity::class.java)
            startActivity(intent)
        }
        val alert = builder.create()
        alert.show()
    }

    @SuppressLint("SimpleDateFormat")
    fun absenSiswa() {

        binding.progress.visibility = View.VISIBLE

        val sdf = SimpleDateFormat("hh:mm:ss")
        val time = sdf.format(Date())

        val pref = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        val namaSiswa = pref.getString("nama_akun", null)

        val responseListener = Response.Listener<String> { response ->
            try {
                val jsonResponse = JSONObject(response)
                val success = jsonResponse.getBoolean("success")
                if (success) {
                    binding.progress.visibility = View.INVISIBLE
                    val builder = AlertDialog.Builder(this@AbsenSiswaActivity)
                    builder.setMessage("Anda Telah Absen")
                        .setNegativeButton("OK", null)
                        .create()
                        .show()
                } else {
                    binding.progress.visibility = View.INVISIBLE
                    val builder = AlertDialog.Builder(this@AbsenSiswaActivity)
                    builder.setMessage("Gagal Terhubung Ke Server")
                        .setNegativeButton("Silahkan Cek Koneksi Internet Anda", null)
                        .create()
                        .show()
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        val absensiswaRequest = AbsenSiswaRequest(
            binding.absnIdMapel.text.toString(),
            namaSiswa,
            binding.absnKelasMapel.text.toString(),
            binding.absnNamaMapel.text.toString(),
            time ,responseListener
        )
        val queue = Volley.newRequestQueue(this@AbsenSiswaActivity)
        queue.add(absensiswaRequest)
    }

}