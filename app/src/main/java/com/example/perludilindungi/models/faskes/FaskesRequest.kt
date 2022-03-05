package com.example.perludilindungi.models.faskes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FaskesRequest {
    @SerializedName("province")
    @Expose
    var province: String? = null

    @SerializedName("city")
    @Expose
    var city: String? = null
}