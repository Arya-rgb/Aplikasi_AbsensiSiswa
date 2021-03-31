package com.project.aplikasiabsensi.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.aplikasiabsensi.R
import com.project.aplikasiabsensi.databinding.ActivityAbsensiBinding
import com.project.aplikasiabsensi.model.AbsenResponse
import com.project.aplikasiabsensi.model.PostResponse
import com.project.aplikasiabsensi.model.service.RetrofitClient
import com.project.aplikasiabsensi.viewmodel.AbsenAktifAdapter
import com.project.aplikasiabsensi.viewmodel.PelajaranAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AbsensiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAbsensiBinding
    private var list = ArrayList<AbsenResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAbsensiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val rv = findViewById<RecyclerView>(R.id.rv_absenaktif)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        RetrofitClient.instance.getActive().enqueue(object : Callback<ArrayList<AbsenResponse>> {

            override fun onResponse(
                call: Call<ArrayList<AbsenResponse>>,
                response: Response<ArrayList<AbsenResponse>>
            ) {
                val listResponse = response.body()
                listResponse?.let { list.addAll(it) }
                val adapter = AbsenAktifAdapter(list)
                rv.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<AbsenResponse>>, t: Throwable) {
                t.message?.let { Log.d("Failure", it) }
            }
        })

    }
}
