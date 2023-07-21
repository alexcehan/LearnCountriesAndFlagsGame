package com.example.learncountriesandflagsgame.data.sources.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: RestCountriesApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://restcountries.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestCountriesApi::class.java)
    }
}