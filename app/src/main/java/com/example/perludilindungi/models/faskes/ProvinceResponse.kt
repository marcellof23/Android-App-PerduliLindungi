package com.example.perludilindungi.models.faskes

import com.example.perludilindungi.models.news.NewsData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProvinceResponse {
    @SerializedName("curr_val")
    @Expose
    var curr_val: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("results")
    @Expose
    var results: List<ProvinceData>? = null
}

class ProvinceData {
    @SerializedName("key")
    @Expose
    var key: String? = null

    @SerializedName("value")
    @Expose
    var value: String? = null
}