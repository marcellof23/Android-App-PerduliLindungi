package com.example.perludilindungi.database.fakses

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.perludilindungi.database.BaseDao

@Dao
interface FaskesDAO : BaseDao<Faskes> {
    @Query("SELECT * FROM faskes_table")
    fun getFaskeses(): LiveData<List<Faskes>>


    @Query("SELECT * FROM faskes_table")
    fun showFaskeses(): List<Faskes>

    @Query("SELECT * FROM faskes_table WHERE id = :faskes_id")
    fun getFaskesById(faskes_id: Long): LiveData<Faskes>

    @Query("SELECT * FROM faskes_table WHERE province = :province AND city = :city")
    fun getFaskesesByProvinceAndCity(province: String, city: String): LiveData<List<Faskes>>

    @Insert
    fun insertAll(faskeses : Faskes) : Long


    @Query("INSERT INTO faskes_table VALUES (:id,  :name, :code, :address, :number, :faskesType, :status, :province, :city)")
    fun insertBookmark(id: Long, name: String, code: String, address: String, number: String, faskesType: String, status: String, province: String, city: String): Void

    @Query("DELETE FROM faskes_table WHERE id = :id")
    fun deleteBookmark(id: Long): Void

    @Query("SELECT * FROM faskes_table WHERE code = :kode limit 1")
    fun findFaskesByCode(kode: String): Faskes
}
