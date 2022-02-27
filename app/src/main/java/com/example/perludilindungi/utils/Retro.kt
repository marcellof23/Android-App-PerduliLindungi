package com.example.perludilindungi.utils

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retro {
    fun getRetroClientInstance(): Retrofit {
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder().baseUrl("https://perludilindungi.herokuapp.com/")
            .addConverterFactory(
                GsonConverterFactory
                    .create(gson)
            ).build()
    }
}