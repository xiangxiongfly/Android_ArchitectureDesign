package com.example.mvvm_clean.data.datasource.remote

import com.example.common.http.HttpManager
import com.example.mvvm_clean.data.api.LoginApi
import com.example.mvvm_clean.data.model.bean.BeanFactory
import com.example.mvvm_clean.data.model.bean.User

object LoginRemoteDataSource {

    private val loginApi: LoginApi by lazy {
        HttpManager.create(LoginApi::class.java)
    }

    suspend fun login(username: String, password: String): BeanFactory<User> {
        return loginApi.login(username, password)
    }
}