package com.example.mvvm_coroutines_retrofit_flow_hilt.model

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.ArticleBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.network.webservice.ArticleApi
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleModel @Inject constructor() : BaseModel() {

    @Inject
    lateinit var articleApi: ArticleApi

    suspend fun getArticleList(): Flow<ResultState<ArrayList<ArticleBean>>> {
        val cacheName = "article_list"
        return requestForResult(cacheName, {
            cacheHelper.getCache<ArrayList<ArticleBean>>(
                cacheName, object : TypeToken<ArrayList<ArticleBean>>() {}.type
            )
        }, {
            articleApi.getArticleList()
        })
    }
}
