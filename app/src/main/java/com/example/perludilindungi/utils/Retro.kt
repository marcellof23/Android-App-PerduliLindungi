package com.example.perludilindungi.utils

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://perludilindungi.herokuapp.com/"

class Retro {

    fun getRetroClientInstance(): Retrofit {

        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory
                    .create(gson)
            ).build()
    }
}