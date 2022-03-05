package com.example.perludilindungi.models.faskes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FaskesCityResponse {
    @SerializedName("curr_val")
    @Expose
    var curr_val: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("results")
    @Expose
    var results: ArrayList<Result>? = null
}
