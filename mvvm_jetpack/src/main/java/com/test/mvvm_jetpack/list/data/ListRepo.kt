package com.test.mvvm_jetpack.list.data

import com.test.mvvm_jetpack.common.http.exceptions.ExceptionHandler
import com.test.mvvm_jetpack.common.model.state.ResultState
import com.test.mvvm_jetpack.list.data.api.ListApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ListRepo(private val listApi: ListApi) {
    fun getArticles(page: Int) = flow {
        val res = listApi.getArticles(page)
        if (res.isSuccessful()) {
            emit(ResultState.Success(res.data))
        } else {
            emit(ResultState.Error(res.errorMsg, res.errorCode))
        }
    }.catch {
        val e = ExceptionHandler.handleException(it)
        emit(ResultState.Error(e.errMsg, e.errCode))
    }.flowOn(Dispatchers.IO)
}