package com.example.mvvm_coroutines_retrofit_livedata.model

import com.example.common.network.HttpManager
import com.example.mvvm_coroutines_retrofit_livedata.base.BaseModel
import com.example.mvvm_coroutines_retrofit_livedata.entity.bean.BaseResponse
import com.example.mvvm_coroutines_retrofit_livedata.entity.bean.User

/**
 * Model层
 */
class LoginModel : BaseModel() {

    private val loginApi: LoginApi by lazy {
        HttpManager.create(LoginApi::class.java)
    }

    suspend fun login(username: String, password: String): BaseResponse<User> {
        return loginApi.login(username, password)
    }
}