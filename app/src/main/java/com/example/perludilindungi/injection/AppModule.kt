package com.example.perludilindungi.injection

import android.content.Context
import androidx.room.Room
import com.example.perludilindungi.database.AppDatabase
import com.example.perludilindungi.utils.Constants.APP_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePerluDilindungiDB(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        APP_DB_NAME
    ).build()

    @Singleton
    @Provides
    fun provideFaskesDAO(db: AppDatabase) = db.faskesDao()

}