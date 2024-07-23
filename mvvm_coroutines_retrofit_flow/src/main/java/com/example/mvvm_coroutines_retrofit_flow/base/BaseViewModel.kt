package com.example.mvvm_coroutines_retrofit_flow.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_coroutines_retrofit_flow.entity.bean.BaseResponse
import com.example.mvvm_coroutines_retrofit_flow.entity.state.ResultState
import com.example.mvvm_coroutines_retrofit_livedata.http.exceptions.ExceptionHandler
import com.example.mvvm_coroutines_retrofit_livedata.http.exceptions.ServerException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * 封装ViewModel
 */
open class BaseViewModel : ViewModel() {

    fun launchMain(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            block()
        }
    }

    fun launchIO(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            block()
        }
    }

    fun launchDefault(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.Default) {
            block()
        }
    }

    fun <T> apiCall(block: suspend () -> BaseResponse<T>): Flow<ResultState<T>> {
        return flow<ResultState<T>> {
            val userResponse = block()
            if (userResponse.isSuccessful()) {
                emit(ResultState.Success(userResponse.data!!))
            } else {
                val serverException = ServerException(userResponse.errorCode, userResponse.errorMsg)
                val e = ExceptionHandler.handleException(serverException)
                emit(ResultState.Error(e, e.errMsg))
            }
        }.flowOn(Dispatchers.IO)
            .onStart {
                emit(ResultState.Loading)
            }.catch {
                val e = ExceptionHandler.handleException(it)
                emit(ResultState.Error(e, e.errMsg))
            }
    }
}