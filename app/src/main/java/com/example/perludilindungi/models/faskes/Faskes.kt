package com.example.perludilindungi.models.faskes

import com.example.perludilindungi.database.fakses.Faskes
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Faskes {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("kode")
    @Expose
    var code: String? = null

    @SerializedName("nama")
    @Expose
    var name: String? = null

    @SerializedName("kota")
    @Expose
    var city: String? = null

    @SerializedName("provinsi")
    @Expose
    var province: String? = null

    @SerializedName("alamat")
    @Expose
    var address: String? = null

    @SerializedName("jenis_faskes")
    @Expose
    var type: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}