package com.example.perludilindungi.ui.location

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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
import com.example.perludilindungi.services.FaskesAPI
import com.example.perludilindungi.utils.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ArrayAdapter
import kotlin.math.sqrt


class LocationFragment : Fragment() {

    private lateinit var locationViewModel: LocationViewModel
    private var _binding: FragmentLocationBinding? = null
    val listFaskes = ArrayList<FaskesItem?>()
    val listProvince = ArrayList<String?>()
    var listCity = ArrayList<String?>()
    var selectedProvince: Any = "DKI JAKARTA"
    var selectedCity: Any = "KOTA ADM. JAKARTA PUSAT"

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

        binding.buttonSearch.setOnClickListener {
            selectedProvince = binding.spinnerProvinsi.selectedItem
            selectedCity = binding.spinnerKota.selectedItem
            getFaskesApi(selectedProvince as String,  selectedCity as String)
        }

        binding.spinnerProvinsi.onItemSelectedListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedProvince = binding.spinnerProvinsi.selectedItem
                selectedCity = binding.spinnerKota.selectedItem
                Log.d("provinsi dipilih", selectedProvince as String)
                getCityApi(selectedProvince as String)
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedProvince = binding.spinnerProvinsi.selectedItem
                selectedCity = binding.spinnerKota.selectedItem
                Log.d("provinsi dipilih", selectedProvince as String)
                getCityApi(selectedProvince as String)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        getProvinceApi()
        getCityApi(selectedProvince as String)

        return root
    }
    fun getFaskesApi(province: String,  city: String)  {
        val retro = Retro().getRetroClientInstance().create(FaskesAPI::class.java)

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
                    listFaskes.clear()
                    for (i in 0 until res.count_total!!) {
                        listFaskes.add(data?.get(i))
                        Log.d("HEI,", data?.get(i)?.id.toString())
                        if(res.count_total?.minus(1) == i){
                            var lat: Double = -6.882417
                            var long: Double = 107.612653

                            var sorted = listFaskes.sortedWith(compareBy { sqrt((it?.latitude?.toDouble()!! - lat) * (it?.latitude?.toDouble()!! - lat) + (it?.longitude?.toDouble()!! - long) * (it?.longitude?.toDouble()!! - long)) })
                            Log.d("sortCheck", sorted.get(0)?.latitude.toString())
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

    fun getCityApi(province : String)  {
        Log.d("provinsiii", province)
        val retro = Retro().getRetroClientInstance().create(FaskesAPI::class.java)

        var locationSpinner: Spinner = binding.spinnerKota

        retro.getFaskesCity(province).enqueue(object : Callback<FaskesCityResponse> {
            override fun onResponse(
                call: Call<FaskesCityResponse>, response: Response<FaskesCityResponse>
            ) {
                val res = response.body()
                val data = res?.results;
                listCity.clear()
                for (i in 0 until res?.results?.size!!) {
                    listCity.add(data?.get(i)?.key)
                    if(res?.results?.size!!.minus(1) == i){
                        val spinnerArrayAdapter: ArrayAdapter<String>? = context?.let {
                            ArrayAdapter<String>(
                                it, R.layout.simple_spinner_item, listCity
                            )
                        }
                        spinnerArrayAdapter?.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
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
                listProvince.clear()
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

//    fun getNearestFaskes(arr: ArrayList<FaskesItem?>, currLat: Double, currLong: Double): ArrayList<FaskesItem?> {
//        var newArr = ArrayList<FaskesItem?>()
//        var distanceArr = ArrayList<Double>()
//        var idxArr = ArrayList<Int>()
//        for(i in arr) {
//            var lat = i?.latitude?.toDouble()!!
//            var long = i?.longitude?.toDouble()!!
//            var distance = sqrt((currLat - lat)*(currLat - lat) + (currLong - long)*(currLong - long))
//            distanceArr.add(distance)
//        }
//        for(j in 1..5) {
//            var idx = -1
//            var nearDist: Double = distanceArr.get(0)
//            for(i in 1..distanceArr.size) {
//                if((distanceArr.get(i) <= nearDist) && idxArr.indexOf(i) != -1)
//                    nearDist = (distanceArr.get(i))
//                    idx += i
//                    idx = idx - idx + i
//            }
//            if(idx != -1) {
//                idxArr.add(idx)
//                newArr.add(arr.get(idx))
//            }
//        }
//        return newArr
//    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}