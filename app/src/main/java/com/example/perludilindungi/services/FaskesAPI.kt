package com.example.perludilindungi.services

import com.example.perludilindungi.models.checkin.CheckInRequest
import com.example.perludilindungi.models.checkin.FaskesRequest
import com.example.perludilindungi.models.checkin.FaskesResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query

interface FaskesAPI {

    @GET("api/get-faskes-vaksinasi")
    fun getFaskesVaksinasi(@Query("province") province: String,
                           @Query("city") city: String) : Call<FaskesResponse>
}