package com.example.perludilindungi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.perludilindungi.database.fakses.Faskes
import com.example.perludilindungi.database.fakses.FaskesDAO
import kotlinx.coroutines.CoroutineScope

/**
 * The Room database that contains the Data table
 */
@Database(entities = [Faskes::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun faskesDao(): FaskesDAO
}