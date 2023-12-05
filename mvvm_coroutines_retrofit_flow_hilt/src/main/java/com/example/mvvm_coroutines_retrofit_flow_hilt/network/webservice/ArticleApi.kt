package com.example.mvvm_coroutines_retrofit_flow_hilt.network.webservice

import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.ArticleBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.BaseResponse
import retrofit2.http.GET

interface ArticleApi {
    @GET("wxarticle/chapters/json")
    suspend fun getArticleList(): BaseResponse<ArrayList<ArticleBean>>
}

