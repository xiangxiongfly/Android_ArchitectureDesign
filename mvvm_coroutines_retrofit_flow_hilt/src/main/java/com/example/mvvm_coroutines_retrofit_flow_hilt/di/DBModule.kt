package com.example.mvvm_coroutines_retrofit_flow_hilt.di

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.HiltApplication
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.db.AppDatabase
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.db.CacheDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides
    fun provideDB(application: HiltApplication): AppDatabase {
        return AppDatabase.getDatabase(application)
    }

    @Singleton
    @Provides
    fun provideCacheDao(db: AppDatabase): CacheDao {
        return db.cacheDao()
    }
}