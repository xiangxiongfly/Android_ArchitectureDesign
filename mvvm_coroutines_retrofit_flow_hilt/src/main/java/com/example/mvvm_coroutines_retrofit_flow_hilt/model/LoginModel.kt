package com.example.mvvm_coroutines_retrofit_flow_hilt.model

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.User
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.repository.LoginApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Model层
 */
class LoginModel @Inject constructor() : BaseModel() {
    @Inject
    lateinit var loginApi: LoginApi

    suspend fun login(username: String, password: String): Flow<ResultState<User>> {
        return requestForResult {
            loginApi.login(username, password)
        }
    }
}