package com.example.perludilindungi.ui.bookmark

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class BookmarkViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bookmark Faskes"
    }

    val text: LiveData<String> = _text

    fun getBookMarkApi() = viewModelScope.launch {
        val retro = Retro().getRetroClientInstance().create(FaskesAPI::class.java)

        val province = "DKI JAKARTA"
        val city = "KOTA ADM. JAKARTA PUSAT"

        retro.getFaskesVaksinasi(province, city).enqueue(object : Callback<FaskesResponse> {
            override fun onResponse(
                call: Call<FaskesResponse>, response: Response<FaskesResponse>
            ) {
                val res = response.body()
                val success = res?.success

                if (success == null) {
                    Log.d("data", "null");
                }

                else if (success == true) {
                    val data = res.data
                    Log.d("data", data?.get(0).toString());
                }
            }

            override fun onFailure(call: Call<FaskesResponse>, t: Throwable) {
                Log.d("EROR", "ERRORRRRRRRRRRRRRRRRR");
            }
        })

    }
}