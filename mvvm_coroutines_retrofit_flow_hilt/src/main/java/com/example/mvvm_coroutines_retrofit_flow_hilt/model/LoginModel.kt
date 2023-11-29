package com.example.mvvm_coroutines_retrofit_flow_hilt.model

import com.example.mvvm_coroutines_retrofit_flow_hilt.network.HttpManager
import com.example.mvvm_coroutines_retrofit_flow_hilt.network.LoginApi
import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.entity.ResultState
import com.example.mvvm_coroutines_retrofit_flow_hilt.entity.bean.User
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