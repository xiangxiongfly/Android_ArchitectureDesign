package com.test.mvvm_jetpack.list.data.api

import com.test.mvvm_jetpack.common.model.BaseResponse
import com.test.mvvm_jetpack.list.data.model.ArticleData
import retrofit2.http.GET
import retrofit2.http.Path

interface ListApi {
    @GET("article/list/{page}/json")
    suspend fun getArticles(@Path("page") page: Int): BaseResponse<ArticleData>
}