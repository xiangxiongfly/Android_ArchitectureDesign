package com.example.mvvm_coroutines_retrofit_flow_hilt.di

import android.app.Application
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.db.AppDatabase
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.db.CacheDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Singleton
    @Provides
    fun provideDB(application: Application): AppDatabase {
        return AppDatabase.getDatabase(application)
    }

    @Singleton
    @Provides
    fun provideCacheDao(db: AppDatabase): CacheDao {
        return db.cacheDao()
    }
}