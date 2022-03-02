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

    val list = ArrayList<Users>()
    val listUsers = arrayOf(
        "Google",
        "Apple",
        "Microsoft",
        "Asus",
        "Zenpone",
        "Acer"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bookmarkTextView = findViewById<RecyclerView>(R.id.recycler_view_bookmark);

        bookmarkTextView.setHasFixedSize(true)
        bookmarkTextView.layoutManager = LinearLayoutManager(this)

        for (i in 0 until listUsers.size){

            list.add(Users(listUsers.get(i)))

            if(listUsers.size - 1 == i){
                // init adapter yang telah dibuat tadi
                val adapter = Adapter(list)
                adapter.notifyDataSetChanged()

                //tampilkan data dalam recycler view
                bookmarkTextView.adapter = adapter
            }
        }
    }
}