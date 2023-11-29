package com.example.mvvm_coroutines_retrofit_flow_hilt.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    companion object {
        lateinit var app: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

}