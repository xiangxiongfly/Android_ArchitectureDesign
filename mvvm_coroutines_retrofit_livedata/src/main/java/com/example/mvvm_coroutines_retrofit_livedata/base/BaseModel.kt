package com.example.mvvm_coroutines_retrofit_livedata.base

import com.example.mvvm_coroutines_retrofit_livedata.entity.ResultState
import com.example.mvvm_coroutines_retrofit_livedata.entity.bean.BaseResponse
import com.example.mvvm_coroutines_retrofit_livedata.http.exceptions.ExceptionHandler
import com.example.mvvm_coroutines_retrofit_livedata.http.exceptions.ServerException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 封装Model
 */
open class BaseModel {

    suspend fun <T> requestForResult(block: suspend () -> BaseResponse<T>): ResultState {
        return withContext(Dispatchers.IO) {
            try {
                val response = block()
                if (response.isSuccessful()) {
                    ResultState.Success(response.data)
                } else {
                    val serverException = ServerException(response.errorCode, response.errorMsg)
                    val e = ExceptionHandler.handleException(serverException)
                    ResultState.Error(e, e.displayMessage)
                }
            } catch (e: Exception) {
                val e = ExceptionHandler.handleException(e)
                ResultState.Error(e, e.displayMessage)
            }
        }
    }
}