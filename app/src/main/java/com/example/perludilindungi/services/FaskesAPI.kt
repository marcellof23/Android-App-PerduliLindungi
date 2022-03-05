package com.example.perludilindungi.services

import com.example.perludilindungi.models.faskes.FaskesCityResponse
import com.example.perludilindungi.models.faskes.FaskesProvinceResponse
import com.example.perludilindungi.models.faskes.FaskesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FaskesAPI {

    @GET("api/get-faskes-vaksinasi")
    fun getFaskesVaksinasi(@Query("province") province: String,
                           @Query("city") city: String) : Call<FaskesResponse>

    @GET("api/get-province")
    fun getFaskesProvince() : Call<FaskesProvinceResponse>

    @GET("api/get-city")
    fun getFaskesCity(@Query("start_id") start_id: String) : Call<FaskesCityResponse>
}