package com.example.mvvm_coroutines_retrofit_livedata.model

import com.example.mvvm_coroutines_retrofit_livedata.base.BaseModel
import com.example.mvvm_coroutines_retrofit_livedata.entity.ResultState
import com.example.mvvm_coroutines_retrofit_livedata.entity.bean.User
import com.example.mvvm_coroutines_retrofit_livedata.http.HttpManager

class LoginModel : BaseModel() {

    private val loginApi: LoginApi by lazy {
        HttpManager.create(LoginApi::class.java)
    }

    suspend fun login(username: String, password: String): ResultState {
        return requestForResult<User> {
            loginApi.login(username, password)
        }
    }
}