package com.example.perludilindungi.ui.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.R
import com.example.perludilindungi.adapter.Adapter
import com.example.perludilindungi.databinding.ActivityMainBinding
import com.example.perludilindungi.databinding.FragmentBookmarkBinding
import com.example.perludilindungi.models.Users

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: FragmentBookmarkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}