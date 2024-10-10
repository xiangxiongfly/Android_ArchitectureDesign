package com.example.mvvm_coroutines_retrofit_flow_hilt.data.respository.remote

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseRepository
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.api.LoginApi
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.BeanFactory
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.UserBean
import javax.inject.Inject

/**
 * Model层
 */
class LoginRepo @Inject constructor() : BaseRepository() {
    @Inject
    lateinit var loginApi: LoginApi

    suspend fun login(username: String, password: String): BeanFactory<UserBean> {
        return loginApi.login(username, password)
    }
}