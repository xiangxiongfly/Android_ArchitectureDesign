package com.example.mvvm_coroutines_retrofit_flow_hilt.data.api

import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.BeanFactory
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.UserBean
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BeanFactory<UserBean>
}