package com.example.mvvm_jetpack.data.datasource.remote

import com.example.common.http.HttpManager
import com.example.common.http.exceptions.ExceptionHandler
import com.example.mvvm_jetpack.data.api.LoginApi
import com.example.mvvm_jetpack.data.model.bean.User
import com.example.mvvm_jetpack.data.state.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object LoginRemoteDataSource {
    private val loginApi: LoginApi by lazy {
        HttpManager.create(LoginApi::class.java)
    }

    suspend fun login(username: String, password: String): ResultState<User> {
        return withContext(Dispatchers.IO) {
            try {
                val result = loginApi.login(username, password)
                if (result.isSuccessful()) {
                    ResultState.Success(result.data!!)
                } else {
                    ResultState.Error(result.errorCode, result.errorMsg)
                }
            } catch (e: Exception) {
                val e = ExceptionHandler.handleException(e)
                ResultState.Error(e.errCode, e.errMsg)
            }
        }
    }
}