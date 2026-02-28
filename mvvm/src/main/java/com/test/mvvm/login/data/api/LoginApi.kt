package com.test.mvvm.login.data.api

import com.test.mvvm.common.model.BaseResponse
import com.test.mvvm.login.data.model.Login
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String): BaseResponse<Login>
}