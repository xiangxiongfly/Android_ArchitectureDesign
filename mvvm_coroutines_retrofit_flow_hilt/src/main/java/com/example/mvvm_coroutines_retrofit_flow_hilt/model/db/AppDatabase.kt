package com.example.mvvm_coroutines_retrofit_flow_hilt.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.entity.CacheEntity

const val VERSION = 1
const val DB_NAME = "cache.db"

@Database(version = VERSION, entities = [CacheEntity::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
            }
            return instance!!
        }
    }

    abstract fun cacheDao(): CacheDao
}