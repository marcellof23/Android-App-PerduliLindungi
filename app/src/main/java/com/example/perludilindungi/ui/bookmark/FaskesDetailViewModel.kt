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

    fun addFaskes(faskes: Faskes){
        viewModelScope.launch(Dispatchers.IO) {
            faskesDB.faskesDao().insert(faskes)
        }
    }

    fun showAllFaskes(){
        viewModelScope.launch(Dispatchers.IO) {
            val tes = faskesDB.faskesDao().showFaskeses()
            Log.d("tagggggg", tes.get(0)?.address.toString())
        }
    }
}