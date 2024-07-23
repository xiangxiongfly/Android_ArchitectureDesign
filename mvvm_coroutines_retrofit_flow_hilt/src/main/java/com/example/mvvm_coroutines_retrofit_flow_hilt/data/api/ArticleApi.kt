package com.example.mvvm_coroutines_retrofit_flow_hilt.data.api

import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.ArticleBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.BaseResponse
import retrofit2.http.GET

interface ArticleApi {
    @GET("wxarticle/chapters/json")
    suspend fun getArticleList(): BaseResponse<ArrayList<ArticleBean>>
}

