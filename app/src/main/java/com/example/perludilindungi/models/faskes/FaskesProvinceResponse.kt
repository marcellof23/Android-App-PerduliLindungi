package com.example.perludilindungi.models.faskes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FaskesProvinceResponse {
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

class Result {
    @SerializedName("key")
    @Expose
    var key: String? = null

    @SerializedName("value")
    @Expose
    var value: String? = null
}