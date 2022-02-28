package com.example.perludilindungi.database.fakses

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.perludilindungi.database.BaseDao

@Dao
interface FaskesDAO : BaseDao<Faskes> {
    @Query("SELECT * FROM faskes_table")
    fun getFaskeses(): LiveData<List<Faskes>>

    @Query("SELECT * FROM faskes_table WHERE id = :faskes_id")
    fun getFaskesById(faskes_id: Long): LiveData<Faskes>

    @Query("SELECT * FROM faskes_table WHERE province = :province AND city = :city")
    fun getFaskesesByProvinceAndCity(province: String, city: String): LiveData<List<Faskes>>
}
