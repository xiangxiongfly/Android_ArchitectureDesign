package com.test.mvvm_clean.data.repository.remote

import com.test.mvvm_clean.data.model.bean.BaseResponse
import com.test.mvvm_clean.data.model.bean.Login
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") username: String, @Field("password") password: String): BaseResponse<Login>
}