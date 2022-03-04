package com.example.perludilindungi.models.news

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONObject
import java.util.*

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
    var description: JSONObject? = null

    @SerializedName("enclosure")
    @Expose
    var enclosure: Enclosure? = null
}

