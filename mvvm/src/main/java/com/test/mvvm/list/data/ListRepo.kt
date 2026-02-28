package com.test.mvvm.list.data

import com.test.mvvm.common.networks.NetworkManager
import com.test.mvvm.list.data.api.ListApi
import com.test.mvvm.list.data.model.ArticleData

class ListRepo {
    val listApi = NetworkManager.createApiService(ListApi::class.java)

    suspend fun getArticles(page: Int): Result<ArticleData> {
        return try {
            val res = listApi.getArticles(page)
            if (res.isSuccessful()) {
                Result.success(res.data)
            } else {
                Result.failure(IllegalArgumentException("参数有误"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}