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
    var nama: String = "KLINIK DPR RI"
    var latitude: String = "-6.2101653"
    var longitude: String = "106.8004706"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailFaskesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var faskesDetailViewModel =
            ViewModelProvider(this).get(FaskesDetailViewModel::class.java)

        binding.buttonBookmark.setOnClickListener{
            val faskes_data = Faskes(12344, "tes", "tes", "tes","tes","tes", "tes", "tes", "tes", )
            faskesDetailViewModel.addFaskes(faskes_data)

        }
        var coordinate: String = "geo:"+latitude+","+longitude+"?q="
        binding.buttonGmaps.setOnClickListener {
            var intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(coordinate + Uri.encode(nama)))
            var chooser: Intent = Intent.createChooser(intent, "Launch Maps")
            startActivity(chooser)
        }
    }
}