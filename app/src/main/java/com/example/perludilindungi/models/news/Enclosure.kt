package com.example.perludilindungi.models.news

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Enclosure {
    @SerializedName("_url")
    @Expose
    var _url: String? = null
    @SerializedName("_length")
    @Expose
    var _length: String? = null
    @SerializedName("_type")
    @Expose
    var _type: String? = null
}