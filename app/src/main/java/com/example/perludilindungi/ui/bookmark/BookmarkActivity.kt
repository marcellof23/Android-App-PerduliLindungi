package com.example.perludilindungi.ui.bookmark

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.perludilindungi.R
import com.example.perludilindungi.adapter.AdapterUser
import com.example.perludilindungi.adapter.FaskesAdapter
import com.example.perludilindungi.database.fakses.Faskes
import com.example.perludilindungi.databinding.ActivityBookmarkBinding
import com.example.perludilindungi.databinding.ActivityMainBinding
import com.example.perludilindungi.databinding.FragmentBookmarkBinding
import com.example.perludilindungi.models.Users
import com.google.android.material.bottomnavigation.BottomNavigationView

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkBinding
    private lateinit var bookmarkViewModel: BookmarkViewModel

    val list = ArrayList<Users>()
    val listAdapter = ArrayList<Faskes>()
    private lateinit var faskesAdapter: FaskesAdapter

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
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_bookmark)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_news, R.id.navigation_location, R.id.navigation_bookmark
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        bookmarkViewModel =
            ViewModelProvider(this).get(BookmarkViewModel::class.java)

        val textView: TextView = binding.textBookmark
        textView.setText("Bookmark Faskes")

        var bookmarkTextView: RecyclerView = binding.recyclerViewBookmark
        bookmarkTextView.setHasFixedSize(true)
        bookmarkTextView.layoutManager = LinearLayoutManager(this)

        for (i in 0 until listUsers.size){

            list.add(Users(listUsers.get(i)))

            if(listUsers.size - 1 == i){
                val adapter = AdapterUser(list)
                adapter.notifyDataSetChanged()

                bookmarkTextView.adapter = adapter
            }
        }
    }
}