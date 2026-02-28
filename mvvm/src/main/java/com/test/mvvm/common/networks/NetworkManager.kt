package com.test.mvvm.common.networks

import com.hjq.gson.factory.GsonFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private const val BASE_URL = "https://www.wanandroid.com/"

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.getSingletonGson()))
            .client(okHttpClient)
            .build()
    }

    fun <T> createApiService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}