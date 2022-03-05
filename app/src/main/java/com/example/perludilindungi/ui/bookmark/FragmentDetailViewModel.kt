package com.example.perludilindungi.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentDetailViewModel  : ViewModel(){
    private val _text = MutableLiveData<String>().apply {
        value = "Bookmark Faskes"
    }

    val text: LiveData<String> = _text
}