package com.example.mvvm_coroutines_retrofit_flow_hilt.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.BaseResponse
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.state.ResultState
import com.example.mvvm_coroutines_retrofit_livedata.http.exceptions.ExceptionHandler
import com.example.mvvm_coroutines_retrofit_livedata.http.exceptions.ServerException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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
            val response = block()
            if (response.isSuccessful()) {
                emit(ResultState.Success(response.data!!))
            } else {
                val serverException = ServerException(response.errorCode, response.errorMsg)
                val e = ExceptionHandler.handleException(serverException)
                emit(ResultState.Error(e, e.errMsg))
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                val e = ExceptionHandler.handleException(it)
                emit(ResultState.Error(e, e.errMsg))
            }
    }


}