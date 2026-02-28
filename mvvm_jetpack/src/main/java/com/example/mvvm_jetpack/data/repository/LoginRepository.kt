package com.example.mvvm_jetpack.data.repository

import com.example.mvvm_jetpack.data.datasource.remote.LoginRemoteDataSource
import com.example.mvvm_jetpack.data.model.bean.User
import com.example.mvvm_jetpack.data.state.ResultState

class LoginRepository(private val remoteSource: LoginRemoteDataSource = LoginRemoteDataSource) {

    suspend fun login(username: String, password: String): ResultState<User> {
        return remoteSource.login(username, password)
    }
}