package com.example.mvvm_coroutines_retrofit_flow.base

import com.example.mvvm_coroutines_retrofit_flow.entity.ResultState
import com.example.mvvm_coroutines_retrofit_flow.entity.bean.BaseResponse
import com.example.mvvm_coroutines_retrofit_flow.network.exceptions.ExceptionHandler
import com.example.mvvm_coroutines_retrofit_flow.network.exceptions.ServerException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * 封装Model
 */
open class BaseModel {

    fun <T> requestForResult(block: suspend () -> BaseResponse<T>): Flow<ResultState<T>> {
        return flow<ResultState<T>> {
            val userResponse = block()
            if (userResponse.isSuccessful()) {
                emit(ResultState.Success(userResponse.data!!))
            } else {
                val serverException = ServerException(userResponse.errorCode, userResponse.errorMsg)
                val e = ExceptionHandler.handleException(serverException)
                emit(ResultState.Error(e, e.displayMessage))
            }
        }.flowOn(Dispatchers.IO)
            .onStart {
                emit(ResultState.Loading)
            }.catch {
                val e = ExceptionHandler.handleException(it)
                emit(ResultState.Error(e, e.displayMessage))
            }
    }
}