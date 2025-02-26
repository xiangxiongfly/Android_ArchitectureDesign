package com.example.mvvm_clean_hilt.data.di

import com.example.common.http.HttpManager
import com.example.mvvm_clean_hilt.data.api.ArticleApi
import com.example.mvvm_clean_hilt.data.api.LoginApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return HttpManager.okHttpClient
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return HttpManager.retrofit
    }

    @Singleton
    @Provides
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Singleton
    @Provides
    fun provideArticleApi(retrofit: Retrofit): ArticleApi {
        return retrofit.create(ArticleApi::class.java)
    }
}