package com.test.mvvm_clean.di

import com.hjq.gson.factory.GsonFactory
import com.test.mvvm_clean.data.repository.ListRepoImpl
import com.test.mvvm_clean.data.repository.LoginRepoImpl
import com.test.mvvm_clean.data.repository.remote.ListApi
import com.test.mvvm_clean.data.repository.remote.LoginApi
import com.test.mvvm_clean.domain.usecase.ListUseCase
import com.test.mvvm_clean.domain.usecase.LoginUseCase
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
//    private const val BASE_URL = "https://www.wanandroid.com/"
    private const val BASE_URL = "https://wanandroid.com/"

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
    fun provideListRepo(listApi: ListApi): ListRepoImpl {
        return ListRepoImpl(listApi)
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
    fun provideListApi(retrofit: Retrofit): ListApi {
        return retrofit.create(ListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideListUseCase(listRepo: ListRepoImpl): ListUseCase {
        return ListUseCase(listRepo)
    }

    @Provides
    @Singleton
    fun provideLoginUseCase(loginRepo: LoginRepoImpl): LoginUseCase {
        return LoginUseCase(loginRepo)
    }
}