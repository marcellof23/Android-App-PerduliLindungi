package com.example.perludilindungi.models.news

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Enclosure {
    @SerializedName("_url")
    @Expose
    var url: String? = null
    @SerializedName("_length")
    @Expose
    var length: String? = null
    @SerializedName("_type")
    @Expose
    var type: String? = null
}