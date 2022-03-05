package com.example.perludilindungi.ui.location

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.adapter.FaskesAdapter
import com.example.perludilindungi.databinding.FragmentLocationBinding
import com.example.perludilindungi.models.faskes.FaskesCityResponse
import com.example.perludilindungi.models.faskes.FaskesItem
import com.example.perludilindungi.models.faskes.FaskesProvinceResponse
import com.example.perludilindungi.models.faskes.FaskesResponse
import com.example.perludilindungi.models.faskes.Result
import com.example.perludilindungi.services.FaskesAPI
import com.example.perludilindungi.utils.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ArrayAdapter




class LocationFragment : Fragment() {

    private lateinit var locationViewModel: LocationViewModel
    private var _binding: FragmentLocationBinding? = null
    val listFaskes = ArrayList<FaskesItem?>()
    val listProvince = ArrayList<String?>()
    val listCity = ArrayList<String?>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        locationViewModel =
            ViewModelProvider(this).get(LocationViewModel::class.java)

        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.cariFaskes
        locationViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        getProvinceApi()
        getCityApi()
//        getFaskesApi()

        return root
    }
    fun getFaskesApi()  {
        val retro = Retro().getRetroClientInstance().create(FaskesAPI::class.java)
        val province = "DKI JAKARTA"
        val city = "KOTA ADM. JAKARTA PUSAT"

        var bookmarkTextView: RecyclerView = binding.recyclerViewLocation
        bookmarkTextView.setHasFixedSize(true)
        bookmarkTextView.layoutManager = LinearLayoutManager(context)

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
                    val data = res.data;
                    Log.d("data_fakses", data?.get(0)?.code.toString());
                    Log.d("data_faskes", data?.get(1)?.code.toString());
                    for (i in 0 until res.count_total!!) {
                        listFaskes.add(data?.get(i))
                        if(res.count_total?.minus(1) == i){
                            val adapter = FaskesAdapter(listFaskes)
                            adapter.notifyDataSetChanged()
                            bookmarkTextView.adapter = adapter
                        }
                    }
                }
            }

            override fun onFailure(call: Call<FaskesResponse>, t: Throwable) {
                Log.d("Error", "true");
            }
        })
    }

    fun getCityApi()  {
        val retro = Retro().getRetroClientInstance().create(FaskesAPI::class.java)
        val province = "DKI JAKARTA"

        var locationSpinner: Spinner = binding.spinnerKota

        retro.getFaskesCity(province).enqueue(object : Callback<FaskesCityResponse> {
            override fun onResponse(
                call: Call<FaskesCityResponse>, response: Response<FaskesCityResponse>
            ) {
                val res = response.body()
                val data = res?.results;
                for (i in 0 until res?.results?.size!!) {
                    listCity.add(data?.get(i)?.key)
                    if(res?.results?.size!!.minus(1) == i){
                        val spinnerArrayAdapter: ArrayAdapter<String>? = context?.let {
                            ArrayAdapter<String>(
                                it, R.layout.simple_spinner_item, listCity
                            )
                        }
                        spinnerArrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        locationSpinner.adapter = spinnerArrayAdapter
                    }
                }
            }

            override fun onFailure(call: Call<FaskesCityResponse>, t: Throwable) {
                Log.d("Error", "true");
            }
        })
    }

    fun getProvinceApi()  {
        val retro = Retro().getRetroClientInstance().create(FaskesAPI::class.java)

        var locationSpinner: Spinner = binding.spinnerProvinsi

        retro.getFaskesProvince().enqueue(object : Callback<FaskesProvinceResponse> {
            override fun onResponse(
                call: Call<FaskesProvinceResponse>, response: Response<FaskesProvinceResponse>
            ) {
                val res = response.body()
                val data = res?.results;
                for (i in 0 until res?.results?.size!!) {
                    listProvince.add(data?.get(i)?.key)
                    if(res?.results?.size!!.minus(1) == i){
                        val spinnerArrayAdapter: ArrayAdapter<String>? = context?.let {
                            ArrayAdapter<String>(
                                it, R.layout.simple_spinner_item, listProvince
                            )
                        }
                        spinnerArrayAdapter?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        locationSpinner.adapter = spinnerArrayAdapter
                    }
                }

            }

            override fun onFailure(call: Call<FaskesProvinceResponse>, t: Throwable) {
                Log.d("Error", "true");
            }
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}