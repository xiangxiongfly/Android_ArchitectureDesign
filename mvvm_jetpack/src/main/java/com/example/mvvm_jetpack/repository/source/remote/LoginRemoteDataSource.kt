package com.example.mvvm_jetpack.repository.source.remote

import com.example.common.http.HttpManager
import com.example.mvvm_jetpack.entity.bean.User
import com.example.mvvm_jetpack.entity.state.UiState
import com.example.common.http.exceptions.ExceptionHandler
import com.example.common.http.exceptions.ServerException
import com.example.mvvm_jetpack.repository.api.LoginApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object LoginRemoteDataSource {
    private val loginApi: LoginApi by lazy {
        HttpManager.create(LoginApi::class.java)
    }

    suspend fun login(username: String, password: String): UiState<User> {
        return withContext(Dispatchers.IO) {
            try {
                val result = loginApi.login(username, password)
                if (result.isSuccessful()) {
                    UiState.Success(result.data!!)
                } else {
                    val serverException = ServerException(result.errorCode, result.errorMsg)
                    val e = ExceptionHandler.handleException(serverException)
                    UiState.Error(e.errCode, e.errMsg)
                }
            } catch (e: Exception) {
                val e = ExceptionHandler.handleException(e)
                UiState.Error(e.errCode, e.errMsg)
            }
        }
    }
}