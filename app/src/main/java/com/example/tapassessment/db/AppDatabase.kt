package com.example.tapassessment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tapassessment.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao():MovieDao
}