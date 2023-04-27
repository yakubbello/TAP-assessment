package com.example.tapassessment.di

import android.content.Context
import androidx.room.Room
import com.example.tapassessment.db.AppDatabase
import com.example.tapassessment.db.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context):AppDatabase{

        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Application data"
        ).build()
    }



    @Provides
    fun provideAlbumPhotoDao(appDatabase: AppDatabase):MovieDao{
        return appDatabase.movieDao()
    }
}