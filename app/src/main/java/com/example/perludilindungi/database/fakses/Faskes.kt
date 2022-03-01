package com.example.perludilindungi.database.fakses

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.perludilindungi.utils.FaskesType
import java.util.*

@Entity(tableName = "faskes_table")
data class Faskes(
    var name: String,
    var code: String,
    var address: String,
    var number: String,
    var faskesType: FaskesType,
    var status: Long,
    var province: String,
    var city: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
