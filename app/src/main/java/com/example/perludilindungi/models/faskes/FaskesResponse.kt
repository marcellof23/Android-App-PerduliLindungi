package com.example.perludilindungi.models.faskes

import com.example.perludilindungi.database.fakses.Faskes
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.json.JSONArray
import java.util.*
import kotlin.collections.ArrayList

class FaskesResponse {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("count_total")
    @Expose
    var count_total: Int? = null

    @SerializedName("data")
    @Expose
    var data: List<FaskesItem>? = emptyList()
}