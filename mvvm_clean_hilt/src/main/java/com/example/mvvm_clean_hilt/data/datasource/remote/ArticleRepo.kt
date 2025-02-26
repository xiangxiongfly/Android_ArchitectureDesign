package com.example.mvvm_clean_hilt.data.respository.remote

import com.example.mvvm_clean_hilt.base.BaseRepository
import com.example.mvvm_clean_hilt.data.api.ArticleApi
import com.example.mvvm_clean_hilt.data.model.bean.ArticleBean
import com.example.mvvm_clean_hilt.data.model.bean.BeanFactory
import javax.inject.Inject

class ArticleRepo @Inject constructor() : BaseRepository() {

    @Inject
    lateinit var articleApi: ArticleApi

    suspend fun getArticleList(): BeanFactory<ArrayList<ArticleBean>> {
        return articleApi.getArticleList()
    }
}
