package com.test.mvvm_jetpack.di

import com.hjq.gson.factory.GsonFactory
import com.test.mvvm_jetpack.list.data.ListRepo
import com.test.mvvm_jetpack.list.data.api.ListApi
import com.test.mvvm_jetpack.login.data.LoginRepo
import com.test.mvvm_jetpack.login.data.api.LoginApi
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
    fun provideListRepo(listApi: ListApi): ListRepo {
        return ListRepo(listApi)
    }

    @Provides
    @Singleton
    fun provideLoginRepo(loginApi: LoginApi): LoginRepo {
        return LoginRepo(loginApi)
    }

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }

    @Provides
    @Singleton
    fun provideListApi(retrofit: Retrofit): ListApi {
        return retrofit.create(ListApi::class.java)
    }
}