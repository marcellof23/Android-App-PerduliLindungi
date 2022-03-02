package com.example.perludilindungi.ui.bookmark

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.perludilindungi.R
import com.example.perludilindungi.models.checkin.CheckInRequest
import com.example.perludilindungi.models.checkin.CheckInResponse
import com.example.perludilindungi.models.checkin.FaskesRequest
import com.example.perludilindungi.models.checkin.FaskesResponse
import com.example.perludilindungi.services.CheckInAPI
import com.example.perludilindungi.services.FaskesAPI
import com.example.perludilindungi.utils.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookmarkViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is bookmark Fragment"
    }

    fun getBookMarkApi() {
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
                    Log.d("data", data.get(0).toString());

                }
            }

            override fun onFailure(call: Call<FaskesResponse>, t: Throwable) {
                Log.d("EROR", "ERRORRRRRRRRRRRRRRRRR");
            //Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }
        })

    }
    val text: LiveData<String> = _text
}