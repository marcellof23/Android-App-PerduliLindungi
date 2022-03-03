package com.example.perludilindungi.ui.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.IUseBottomNav
import com.example.perludilindungi.R
import com.example.perludilindungi.adapter.AdapterUser
import com.example.perludilindungi.adapter.FaskesAdapter
import com.example.perludilindungi.database.fakses.Faskes
import com.example.perludilindungi.databinding.ActivityBookmarkBinding
import com.example.perludilindungi.databinding.ActivityMainBinding
import com.example.perludilindungi.databinding.FragmentBookmarkBinding
import com.example.perludilindungi.models.Users
import com.example.perludilindungi.models.faskes.FaskesItem
import com.example.perludilindungi.models.faskes.FaskesResponse
import com.example.perludilindungi.services.FaskesAPI
import com.example.perludilindungi.utils.Retro
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookmarkActivity : AppCompatActivity(), IUseBottomNav {
    private lateinit var binding: ActivityBookmarkBinding
    private lateinit var bookmarkViewModel: BookmarkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookmarkViewModel =
            ViewModelProvider(this).get(BookmarkViewModel::class.java)

        val textView: TextView = binding.textBookmark
        textView.setText("Bookmark Faskes")
    }
}