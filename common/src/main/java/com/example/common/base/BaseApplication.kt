package com.example.common.base

import android.app.Application

class BaseApplication : Application() {
    companion object {
        @JvmStatic
        lateinit var instance: BaseApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}