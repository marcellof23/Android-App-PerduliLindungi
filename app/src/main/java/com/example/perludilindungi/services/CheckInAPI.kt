package com.example.perludilindungi.services

import com.example.perludilindungi.models.checkin.CheckInRequest
import com.example.perludilindungi.models.checkin.CheckInResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CheckInAPI {

    @POST("check-in")
    fun sendCheckIn(@Body req: CheckInRequest) : Call<CheckInResponse>
}