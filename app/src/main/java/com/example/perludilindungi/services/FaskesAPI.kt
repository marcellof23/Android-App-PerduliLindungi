package com.example.perludilindungi.services

import com.example.perludilindungi.models.faskes.ProvinceResponse
import com.example.perludilindungi.models.news.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface FaskesAPI {
    @GET("api/get-province")
    fun getProvince() : Call<ProvinceResponse>

    @GET("api/get-city")
    fun getCity() : Call<ProvinceResponse>
}