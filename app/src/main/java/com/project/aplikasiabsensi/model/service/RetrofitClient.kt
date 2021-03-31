package com.project.aplikasiabsensi.model.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://alihyaabsendata.000webhostapp.com/"

    val instance: ApiInterface by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        retrofit.create(ApiInterface::class.java)
    }

}