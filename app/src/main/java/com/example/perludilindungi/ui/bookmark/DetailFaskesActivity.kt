package com.example.perludilindungi.ui.bookmark

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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailFaskesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var faskesDetailViewModel =
            ViewModelProvider(this).get(FaskesDetailViewModel::class.java)

        binding.buttonBookmark.setOnClickListener{
            val faskes_data = Faskes("tes", "tes", "tes","tes","tes", "tes", "tes", "tes")
            faskesDetailViewModel.addFaskes(faskes_data)

        }

        binding.buttonGmaps.setOnClickListener {
            faskesDetailViewModel.showAllFaskes()
        }
    }
}