package com.example.perludilindungi.ui.bookmark

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.perludilindungi.R
import com.example.perludilindungi.database.AppDatabase
import com.example.perludilindungi.database.fakses.Faskes
import com.example.perludilindungi.databinding.FragmentDetailFaskesBinding
import com.example.perludilindungi.injection.AppModule.providePerluDilindungiDB
import com.example.perludilindungi.ui.MainViewModel
import com.example.perludilindungi.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

class DetailFaskesActivity : AppCompatActivity() {
    private lateinit var binding: FragmentDetailFaskesBinding
    private val viewModel: MainViewModel by viewModels()
    var nama: String? = "KLINIK DPR RI"
    var latitude: String? = null
    var longitude: String? = null
    var isBookmark : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailFaskesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id_faskes = intent.getIntExtra("id_faskes", 0)
        val code = intent.getStringExtra("code")
        val address = intent.getStringExtra("address")
        val name = intent.getStringExtra("name")
        val type = intent.getStringExtra("type")
        val number = intent.getStringExtra("number")
        val status = intent.getStringExtra("status")
        val province = intent.getStringExtra("province")
        val city = intent.getStringExtra("city")
        val longitudeIntent = intent.getStringExtra("longitude")
        val latitudeIntent = intent.getStringExtra("latitude")
        binding.faskesDetailName.text = name
        binding.faskesDetailAddress.text= address
        binding.faskesDetailType.text= type
        binding.faskesDetailCode.text= code
        binding.faskesDetailNumber.text= number
        nama = name
        longitude = longitudeIntent
        latitude = latitudeIntent

        var id_faskes_notnull = id_faskes
        var name_notnull = "Bambang"
        if(name != null) {
            name_notnull = name
        }
        var code_notnull = "Bambang"
        if(code != null) {
            code_notnull = code
        }
        var address_notnull = "Bambang"
        if(address != null) {
            address_notnull = address
        }
        var type_notnull = "Bambang"
        if(type != null) {
            type_notnull = type
        }
        var number_notnull = "0"
        if(number != null) {
            number_notnull = number
        }
        var province_notnull = "0"
        if(province != null) {
            province_notnull = province
        }
        var city_notnull = "0"
        if(city != null) {
            city_notnull = city
        }
        var status_notnull = "0"
        if(status != null) {
            status_notnull = status
        }


        var faskesDetailViewModel =
            ViewModelProvider(this).get(FaskesDetailViewModel::class.java)

        Log.d("woi", id_faskes.toString())
        val tes = faskesDetailViewModel.faskesDB.faskesDao().showFaskesById(id_faskes.toLong())
        if(tes != null) {
            binding.buttonBookmark.text = "- Unbookmark"
        }

        if(binding.buttonBookmark.text == "- Unbookmark") {
            binding.buttonBookmark.setOnClickListener{
                faskesDetailViewModel.deleteFaskesById(id_faskes)
                binding.buttonBookmark.text = "+ Bookmark"
                Log.d("EHETE", id_faskes.toString())
            }
        } else {
            binding.buttonBookmark.setOnClickListener{
                if(id_faskes != null) {
                    id_faskes_notnull = id_faskes
                }
                val faskes_data = Faskes(id_faskes_notnull, name_notnull, code_notnull, address_notnull,number_notnull,type_notnull, status_notnull, province_notnull,city_notnull)
                faskesDetailViewModel.addFaskes(faskes_data)
                Log.d("MASUKPAKEKO", id_faskes.toString())
                binding.buttonBookmark.text = "- Unbookmark"
            }
        }


        var coordinate: String = "geo:"+latitude+","+longitude+"?q="
        binding.buttonGmaps.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(coordinate + Uri.encode(nama)))
            var chooser: Intent = Intent.createChooser(intent, "Launch Maps")
            startActivity(chooser)
          //faskesDetailViewModel.faskesDB.faskesDao().deleteAllBookmark()
        }
    }
}