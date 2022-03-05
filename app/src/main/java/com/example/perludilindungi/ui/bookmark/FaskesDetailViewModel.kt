package com.example.perludilindungi.ui.bookmark

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.room.RoomDatabase
import com.example.perludilindungi.database.fakses.Faskes
import com.example.perludilindungi.injection.AppModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FaskesDetailViewModel(application: Application): AndroidViewModel(application) {
    val faskesDB = AppModule.providePerluDilindungiDB(application)
    var faskes_id = -1

    private val _faskeses = MutableLiveData<List<Faskes>>().apply {
        value = showAllFaskes()
    }

    val faskeses: MutableLiveData<List<Faskes>> = _faskeses

    private val _faskes = MutableLiveData<LiveData<Faskes>>().apply {
        value = findFaskesById(faskes_id.toLong())
    }

    val faskes: MutableLiveData<LiveData<Faskes>> = _faskes

    fun addFaskes(faskes: Faskes){
        viewModelScope.launch(Dispatchers.IO) {
            var tes = faskesDB.faskesDao().insertFaskes(faskes)
            Log.d("tagggggg", tes.toString())
        }
    }

    fun showAllFaskes() : List<Faskes>? {
        val tes = faskesDB.faskesDao().showFaskeses()
        return tes
    }

    fun findFaskesById(id : Long) : LiveData<Faskes> {
        val tes = faskesDB.faskesDao().getFaskesById(id)
        return tes
    }

    fun deleteFaskesById(id : Int) {
        faskesDB.faskesDao().deleteBookmark(id)
    }

}