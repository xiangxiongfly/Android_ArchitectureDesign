package com.example.mvvm_coroutines_retrofit_flow_hilt.model

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.ArticleBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.network.webservice.ArticleApi
import com.google.gson.reflect.TypeToken
import com.hjq.gson.factory.GsonFactory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleModel @Inject constructor() : BaseModel() {

    @Inject
    lateinit var articleApi: ArticleApi

    suspend fun getArticleList(): Flow<ResultState<ArrayList<ArticleBean>>> {
        val cacheName = "article_list"
        return requestForResult(cacheName, {
            val json = cacheHelper.getCache(cacheName)
            var articleList: ArrayList<ArticleBean>? = null
            json?.let {
                articleList = GsonFactory.getSingletonGson()
                    .fromJson(json, object : TypeToken<ArrayList<ArticleBean>>() {}.type)
            }
            return@requestForResult articleList
        }, {
            articleApi.getArticleList()
        })
    }
}
