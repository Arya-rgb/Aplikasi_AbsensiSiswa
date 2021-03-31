package com.project.aplikasiabsensi.model.service

import com.project.aplikasiabsensi.model.AbsenResponse
import com.project.aplikasiabsensi.model.AbsensiRequest
import com.project.aplikasiabsensi.model.PostResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("jsondata.php")
    fun getList(): Call<ArrayList<PostResponse>>

    @GET("absenaktif.php")
    fun getActive(): Call<ArrayList<AbsenResponse>>

}