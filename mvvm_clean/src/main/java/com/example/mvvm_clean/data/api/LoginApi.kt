package com.example.mvvm_clean.data.api

import com.example.mvvm_clean.data.model.bean.BaseBean
import com.example.mvvm_clean.data.model.bean.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {
    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): BaseBean<User>
}