package com.example.mvvm_coroutines_retrofit_flow_hilt.network

import com.example.mvvm_coroutines_retrofit_flow_hilt.entity.bean.BaseResponse
import com.example.mvvm_coroutines_retrofit_flow_hilt.entity.bean.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseResponse<User>
}