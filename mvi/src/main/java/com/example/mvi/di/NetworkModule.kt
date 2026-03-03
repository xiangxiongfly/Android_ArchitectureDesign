package com.example.mvi.di

import com.example.mvi.data.repository.LoginRepoImpl
import com.example.mvi.data.repository.remote.LoginApi
import com.example.mvi.domain.usecase.LoginUseCase
import com.hjq.gson.factory.GsonFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://www.wanandroid.com/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonFactory.getSingletonGson()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideLoginRepo(loginApi: LoginApi): LoginRepoImpl {
        return LoginRepoImpl(loginApi)
    }

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(loginRepo: LoginRepoImpl): LoginUseCase {
        return LoginUseCase(loginRepo)
    }
}