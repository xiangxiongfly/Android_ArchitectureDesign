package com.test.mvvm.login.data

import com.example.common.http.exceptions.ExceptionHandler
import com.test.mvvm.common.model.state.ResultState
import com.test.mvvm.common.networks.NetworkManager
import com.test.mvvm.login.data.api.LoginApi
import com.test.mvvm.login.data.model.Login
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepo {
    private val loginApi = NetworkManager.createApiService(LoginApi::class.java)

    suspend fun login(username: String, password: String): ResultState<Login> {
        return withContext(Dispatchers.IO) {
            try {
                val res = loginApi.login(username, password)
                if (res.isSuccessful()) {
                    ResultState.Success(res.data)
                } else {
                    ResultState.Error(res.errorMsg, res.errorCode)
                }
            } catch (e: Exception) {
                val e = ExceptionHandler.handleException(e)
                ResultState.Error(e.errMsg, e.errCode)
            }
        }
    }
}