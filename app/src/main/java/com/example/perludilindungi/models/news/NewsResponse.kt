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
    var results: List<NewsData>? = null
}

class NewsData {
    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("link")
    @Expose
    var link: List<String>? = null

    @SerializedName("guid")
    @Expose
    var guid: String? = null

    @SerializedName("pubDate")
    @Expose
    var pubDate: String? = null

    @SerializedName("description")
    @Expose
    var description: Dictionary<String,String>? = null

    @SerializedName("enclosure")
    @Expose
    var enclosure: Dictionary<String    ,String>? = null
}