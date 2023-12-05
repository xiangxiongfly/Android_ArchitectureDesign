package com.example.mvvm_coroutines_retrofit_flow_hilt.base

import com.example.mvvm_coroutines_retrofit_flow_hilt.model.ResultState
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.BaseResponse
import com.example.mvvm_coroutines_retrofit_flow_hilt.network.exceptions.ExceptionHandler
import com.example.mvvm_coroutines_retrofit_flow_hilt.network.exceptions.ServerException
import com.example.mvvm_coroutines_retrofit_flow_hilt.utils.CacheHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * 封装Model
 */
open class BaseModel {

    @Inject
    lateinit var cacheHelper: CacheHelper

    fun <T> requestForResult(block: suspend () -> BaseResponse<T>): Flow<ResultState<T>> {
        return flow<ResultState<T>> {
            val response = block()
            if (response.isSuccessful()) {
                emit(ResultState.Success(response.data!!))
            } else {
                val serverException = ServerException(response.errorCode, response.errorMsg)
                val e = ExceptionHandler.handleException(serverException)
                emit(ResultState.Error(e, e.displayMessage))
            }
        }.flowOn(Dispatchers.IO)
            .onStart {
                emit(ResultState.Loading)
            }
            .catch {
                val e = ExceptionHandler.handleException(it)
                emit(ResultState.Error(e, e.displayMessage))
            }
    }

    suspend fun <T> requestForResult(
        cacheName: String,
        cacheBlock: () -> T?,
        block: suspend () -> BaseResponse<T>
    ): Flow<ResultState<T>> {
        return flow {
            val cacheData = cacheBlock()
            cacheData?.let {
                emit(ResultState.Success(cacheData, true))
            }

            val response = block()
            if (response.isSuccessful()) {
                cacheHelper.saveCache(cacheName, response.data!!)
                emit(ResultState.Success(response.data, false))
            } else {
                val serverException = ServerException(response.errorCode, response.errorMsg)
                val e = ExceptionHandler.handleException(serverException)
                emit(ResultState.Error(e, e.displayMessage))
            }
        }.flowOn(Dispatchers.IO)
            .onStart {
                emit(ResultState.Loading)
            }
            .catch {
                val e = ExceptionHandler.handleException(it)
                emit(ResultState.Error(e, e.displayMessage))
            }
    }

}