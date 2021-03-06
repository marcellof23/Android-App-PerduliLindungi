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

    @Query("SELECT * FROM faskes_table WHERE id_faskes = :faskes_id")
    fun getFaskesById(faskes_id: Long): LiveData<Faskes>

    @Query("SELECT * FROM faskes_table WHERE id_faskes = :faskes_id")
    fun showFaskesById(faskes_id: Long): Faskes

    @Query("SELECT * FROM faskes_table WHERE province = :province AND city = :city")
    fun getFaskesesByProvinceAndCity(province: String, city: String): LiveData<List<Faskes>>

    @Insert
    fun insertFaskes(faskeses : Faskes) : Long

    @Query("INSERT INTO faskes_table VALUES (:id, :id_faskes, :name, :code, :address, :number, :faskesType, :status, :province, :city)")
    fun insertBookmark(id: Long, id_faskes: Int, name: String, code: String, address: String, number: String, faskesType: String, status: String, province: String, city: String): Void

    @Query("DELETE FROM faskes_table WHERE id_faskes = :id_faskes")
    fun deleteBookmark(id_faskes: Int): Void

    @Query("DELETE FROM faskes_table where id_faskes > 0")
    fun deleteAllBookmark(): Void
}
