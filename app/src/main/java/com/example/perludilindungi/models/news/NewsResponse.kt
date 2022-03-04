package com.example.perludilindungi.models.news

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class NewsResponse {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("count_total")
    @Expose
    var count_total: Int? = null

    @SerializedName("results")
    @Expose
    var results: List<NewsData>? = emptyList()
}

