package com.example.perludilindungi.models.faskes

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

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
    var data: Array<JsonObject> = emptyArray()
}