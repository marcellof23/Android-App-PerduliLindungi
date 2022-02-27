package com.example.perludilindungi.models.checkin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CheckInRequest {
    @SerializedName("qrCode")
    @Expose
    var qrCode: String? = null

    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null

    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
}