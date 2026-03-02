package com.test.mvvm_clean.data.repository

import com.test.mvvm_clean.common.http.exceptions.ExceptionHandler
import com.test.mvvm_clean.data.model.state.ResultState
import com.test.mvvm_clean.data.repository.remote.ListApi
import com.test.mvvm_clean.domain.repository.ListRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ListRepoImpl(private val listApi: ListApi) : ListRepo {
    override fun getArticles(page: Int) = flow {
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