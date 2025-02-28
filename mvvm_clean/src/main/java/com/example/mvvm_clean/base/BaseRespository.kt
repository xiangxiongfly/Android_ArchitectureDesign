package com.example.mvvm_clean.base

import com.example.common.http.exceptions.ExceptionHandler
import com.example.mvvm_clean.data.model.bean.BeanFactory
import com.example.mvvm_clean.data.model.state.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

open class BaseRepository {
    /**
     * 处理远程网络数据
     */
    open fun <T> handleRemoteData(block: suspend () -> BeanFactory<T>) = flow {
        val rep = block()
        if (rep.isSuccessful()) {
            emit(ResultState.Success(rep.data!!))
        } else {
            emit(ResultState.Error(rep.errorCode, rep.errorMsg ?: "未知错误"))
        }
    }.catch {
        val e = ExceptionHandler.handleException(it)
        emit(ResultState.Error(e.errCode, e.errMsg))
    }.flowOn(Dispatchers.IO)
}