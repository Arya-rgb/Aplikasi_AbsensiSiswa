package com.project.aplikasiabsensi.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.aplikasiabsensi.R
import com.project.aplikasiabsensi.databinding.ActivityPelajaranBinding
import com.project.aplikasiabsensi.model.PostResponse
import com.project.aplikasiabsensi.model.service.RetrofitClient
import com.project.aplikasiabsensi.viewmodel.PelajaranAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PelajaranActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPelajaranBinding
    private var list = ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPelajaranBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_pelajaran)
        showList()

    }
    private fun showList() {
        val rv = findViewById<RecyclerView>(R.id.rvPelajaran)
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        RetrofitClient.instance.getList().enqueue(object : Callback<ArrayList<PostResponse>> {

            override fun onResponse(call: Call<ArrayList<PostResponse>>, response: Response<ArrayList<PostResponse>>) {
                val listResponse = response.body()
                listResponse?.let { list.addAll(it) }
                val adapter = PelajaranAdapter(list)
                rv.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {
                t.message?.let { Log.d("Failure", it) }
            }
        })
        if (list.size > 0) {
            binding.apply {
            }
        }
    }

}