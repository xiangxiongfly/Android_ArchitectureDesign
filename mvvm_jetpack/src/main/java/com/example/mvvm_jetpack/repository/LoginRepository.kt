package com.example.mvvm_jetpack.repository

import com.example.mvvm_jetpack.entity.bean.User
import com.example.mvvm_jetpack.entity.state.UiState
import com.example.mvvm_jetpack.repository.source.remote.LoginRemoteDataSource

class LoginRepository(private val remoteSource: LoginRemoteDataSource = LoginRemoteDataSource) {

    suspend fun login(username: String, password: String): UiState<User> {
        return remoteSource.login(username, password)
    }
}