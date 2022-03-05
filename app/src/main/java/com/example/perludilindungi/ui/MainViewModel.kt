package com.example.perludilindungi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.example.perludilindungi.database.fakses.Faskes
import com.example.perludilindungi.repository.MainRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    val mainRepo: MainRepo) : ViewModel(){

    fun insertBookmark(faskes: Faskes) = viewModelScope.launch {
        mainRepo.insertBookmarkFaskes(faskes)
    }

    fun deleteBookmark(faskes: Faskes) = viewModelScope.launch {
        mainRepo.deleteBookmarkFaskes(faskes)
    }

    fun getFaskesesRepo() = mainRepo.getFaskeses()
    fun getFaskesByIdRepo(id: Long) = mainRepo.getFaskesById(id)
    fun getFaskesesByProvinceAndCityRepo(province: String, city: String) = mainRepo.getFaskesesByProvinceAndCity(province, city)
}