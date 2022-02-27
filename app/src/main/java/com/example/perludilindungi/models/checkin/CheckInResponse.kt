package com.example.perludilindungi.models.checkin

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CheckInResponse {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("code")
    @Expose
    var code: Int? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("data")
    @Expose
    var data: JsonObject? = null // ini map, key nya string userStatus, reason
}
