package com.example.mvvm_coroutines_retrofit_flow_hilt.data.respository.local

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseRepository
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.ArticleBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.cache.CacheManager
import javax.inject.Inject

class LocalArticleRepo @Inject constructor(val cacheManager: CacheManager) : BaseRepository() {

    fun getCacheArticle(): ArrayList<ArticleBean>? {
        return cacheManager.getCache<ArrayList<ArticleBean>>("article_list")
    }

    fun saveCacheArticle(list: ArrayList<ArticleBean>) {
        cacheManager.saveCache("article_list", list)
    }
}