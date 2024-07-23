package com.example.common.base

import android.app.Application

open class BaseApplication : Application() {
    companion object {
        private lateinit var instance: BaseApplication

        fun getInstance(): BaseApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}