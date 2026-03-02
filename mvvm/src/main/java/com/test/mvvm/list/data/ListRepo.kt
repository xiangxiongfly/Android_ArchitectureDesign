package com.test.mvvm.list.data

import com.example.common.http.exceptions.ExceptionHandler
import com.test.mvvm.common.model.state.ResultState
import com.test.mvvm.common.networks.NetworkManager
import com.test.mvvm.list.data.api.ListApi
import com.test.mvvm.list.data.model.ArticleData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ListRepo {
    val listApi = NetworkManager.createApiService(ListApi::class.java)

    suspend fun getArticles(page: Int): ResultState<ArticleData> {
        return withContext(Dispatchers.IO) {
            try {
                val res = listApi.getArticles(page)
                if (res.isSuccessful()) {
                    ResultState.Success(res.data)
                } else {
                    ResultState.Error(res.errorMsg, res.errorCode)
                }
            } catch (e: Exception) {
                val e = ExceptionHandler.handleException(e)
                ResultState.Error(e.errMsg, e.errCode)
            }
        }
    }
}