package com.example.perludilindungi

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.perludilindungi.database.AppDatabase

import com.example.perludilindungi.databinding.ActivityMainBinding
import com.example.perludilindungi.injection.AppModule.providePerluDilindungiDB
import dagger.hilt.android.AndroidEntryPoint

class MainActivity : AppCompatActivity(), IUseBottomNav {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = providePerluDilindungiDB(applicationContext)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_news, R.id.navigation_location, R.id.navigation_bookmark
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}

class ActivityMainBinding {

}
