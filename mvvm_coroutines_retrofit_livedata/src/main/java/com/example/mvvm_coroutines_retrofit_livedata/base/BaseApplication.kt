package com.example.mvvm_coroutines_retrofit_livedata.base

import android.app.Application

class BaseApplication : Application() {
    companion object {
        lateinit var app: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }

}