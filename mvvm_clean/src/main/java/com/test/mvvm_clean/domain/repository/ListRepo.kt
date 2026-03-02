package com.test.mvvm_clean.domain.repository

import com.test.mvvm_clean.data.model.bean.ArticleData
import com.test.mvvm_clean.data.model.state.ResultState
import kotlinx.coroutines.flow.Flow

interface ListRepo {
    fun getArticles(page: Int): Flow<ResultState<ArticleData>>
}