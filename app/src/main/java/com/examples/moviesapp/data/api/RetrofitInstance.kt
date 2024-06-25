package com.examples.moviesapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://kinopoiskapiunofficial.tech"

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val kinoApi: KinoApi = retrofit.create(KinoApi::class.java)
}