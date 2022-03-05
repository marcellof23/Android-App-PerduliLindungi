package com.example.perludilindungi.repository

import androidx.lifecycle.LiveData
import androidx.room.Room
import com.example.perludilindungi.database.AppDatabase
import com.example.perludilindungi.database.fakses.Faskes
import com.example.perludilindungi.database.fakses.FaskesDAO
import javax.inject.Inject

class MainRepo  @Inject constructor(
    val faskesDAO: FaskesDAO,
) {


    suspend fun insertBookmarkFaskes(faskes: Faskes) = faskesDAO.insert(faskes)
    suspend fun deleteBookmarkFaskes(faskes: Faskes) = faskesDAO.delete(faskes)

    fun getFaskeses() = faskesDAO.getFaskeses()
    fun getFaskesById(id: Long) = faskesDAO.getFaskesById(id)
    fun getFaskesesByProvinceAndCity(province: String, city: String) = faskesDAO.getFaskesesByProvinceAndCity(province, city)
}