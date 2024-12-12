package com.example.mvvm_clean.data.datasource.remote

import com.example.common.http.HttpManager
import com.example.mvvm_clean.data.api.LoginApi
import com.example.mvvm_clean.data.entity.bean.BaseBean
import com.example.mvvm_clean.data.entity.bean.User

object LoginRemoteDataSource {

    private val loginApi: LoginApi by lazy {
        HttpManager.create(LoginApi::class.java)
    }

    suspend fun login(username: String, password: String): BaseBean<User> {
        return loginApi.login(username, password)
    }
}