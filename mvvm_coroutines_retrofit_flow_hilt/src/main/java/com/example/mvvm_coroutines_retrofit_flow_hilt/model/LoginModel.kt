package com.example.mvvm_coroutines_retrofit_flow_hilt.model

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.UserBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.network.webservice.LoginApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Model层
 */
class LoginModel @Inject constructor() : BaseModel() {
    @Inject
    lateinit var loginApi: LoginApi

    suspend fun login(username: String, password: String): Flow<ResultState<UserBean>> {
        return requestForResult {
            loginApi.login(username, password)
        }
    }
}