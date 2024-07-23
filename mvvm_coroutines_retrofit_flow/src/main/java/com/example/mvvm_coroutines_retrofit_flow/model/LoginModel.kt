package com.example.mvvm_coroutines_retrofit_flow.model

import com.example.common.network.HttpManager
import com.example.mvvm_coroutines_retrofit_flow.base.BaseModel
import com.example.mvvm_coroutines_retrofit_flow.entity.bean.BaseResponse
import com.example.mvvm_coroutines_retrofit_flow.entity.bean.User

/**
 * Model层
 */
class LoginModel : BaseModel() {

    private val loginApi: LoginApi by lazy {
        HttpManager.create(LoginApi::class.java)
    }

    suspend fun login(username: String, password: String): BaseResponse<User> {
//        return requestForResult {
//            loginApi.login(username, password)
//        }
        return loginApi.login(username, password)
    }
}