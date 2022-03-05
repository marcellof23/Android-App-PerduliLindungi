package com.example.perludilindungi.ui.bookmark

import android.util.Log
import com.example.perludilindungi.database.fakses.Faskes
import com.example.perludilindungi.models.faskes.FaskesResponse
import com.example.perludilindungi.services.FaskesAPI
import com.example.perludilindungi.utils.Retro
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.perludilindungi.MainActivity

import androidx.recyclerview.widget.LinearLayoutManager

import androidx.recyclerview.widget.RecyclerView

import android.R
import android.app.Application
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.*
import com.example.perludilindungi.injection.AppModule
import kotlinx.coroutines.Dispatchers


class BookmarkViewModel(application: Application): AndroidViewModel(application) {
    private val _text = MutableLiveData<String>().apply {
        value = "Bookmark Faskes"
    }
    val text: LiveData<String> = _text
}

