package com.test.mvvm.login.data

import com.test.mvvm.common.networks.NetworkManager
import com.test.mvvm.login.data.api.LoginApi
import com.test.mvvm.login.data.model.Login

class LoginRepo {
    private val loginApi = NetworkManager.createApiService(LoginApi::class.java)

    suspend fun login(username: String, password: String): Result<Login> {
        return try {
            val response = loginApi.login(username, password)
            if (response.isSuccessful()) {
                Result.success(response.data)
            } else {
                Result.failure(IllegalArgumentException("参数错误"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}