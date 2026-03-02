package com.test.mvvm_clean.data.repository.remote

import com.test.mvvm_clean.data.model.bean.ArticleData
import com.test.mvvm_clean.data.model.bean.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ListApi {
    @GET("article/list/{page}/json")
    suspend fun getArticles(@Path("page") page: Int): BaseResponse<ArticleData>
}