package com.example.mvvm_coroutines_retrofit_flow.model

import com.example.mvvm_coroutines_retrofit_flow.base.BaseModel
import com.example.mvvm_coroutines_retrofit_flow.entity.ResultState
import com.example.mvvm_coroutines_retrofit_flow.entity.bean.User
import com.example.mvvm_coroutines_retrofit_flow.network.HttpManager
import com.example.mvvm_coroutines_retrofit_flow.network.LoginApi
import kotlinx.coroutines.flow.Flow

/**
 * Model层
 */
class LoginModel : BaseModel() {

    private val loginApi: LoginApi by lazy {
        HttpManager.create(LoginApi::class.java)
    }

    suspend fun login(username: String, password: String): Flow<ResultState<User>> {
        return requestForResult {
            loginApi.login(username, password)
        }
    }
}