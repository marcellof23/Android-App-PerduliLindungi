package com.example.perludilindungi.models.faskes

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FaskesItem {
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

    @SerializedName("telp")
    @Expose
    var number: String? = null

    @SerializedName("jenis_faskes")
    @Expose
    var type: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null
}