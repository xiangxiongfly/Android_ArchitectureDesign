package com.example.mvvm_coroutines_retrofit_flow_hilt.di

import android.app.Application
import com.example.mvvm_coroutines_retrofit_flow_hilt.base.HiltApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideApplication(application: Application): HiltApplication {
        return application as HiltApplication
    }
}