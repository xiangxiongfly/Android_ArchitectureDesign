package com.example.mvvm_coroutines_retrofit_flow_hilt.data.respository.remote

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseRepository
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.api.ArticleApi
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.ArticleBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.BeanFactory
import javax.inject.Inject

class ArticleRepo @Inject constructor() : BaseRepository() {

    @Inject
    lateinit var articleApi: ArticleApi

    suspend fun getArticleList(): BeanFactory<ArrayList<ArticleBean>> {
        return articleApi.getArticleList()
    }
}
