package com.example.mvvm_coroutines_retrofit_livedata.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_coroutines_retrofit_livedata.entity.bean.BaseResponse
import com.example.mvvm_coroutines_retrofit_livedata.entity.state.ResultState
import com.example.mvvm_coroutines_retrofit_livedata.http.exceptions.ExceptionHandler
import com.example.mvvm_coroutines_retrofit_livedata.http.exceptions.ServerException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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

    suspend fun <T> apiCall(block: suspend () -> BaseResponse<T>): ResultState {
        return withContext(Dispatchers.IO) {
            try {
                val response = block()
                if (response.isSuccessful()) {
                    ResultState.Success(response.data)
                } else {
                    val serverException = ServerException(response.errorCode, response.errorMsg)
                    val e = ExceptionHandler.handleException(serverException)
                    ResultState.Error(e, e.errMsg)
                }
            } catch (e: Exception) {
                val e = ExceptionHandler.handleException(e)
                ResultState.Error(e, e.errMsg)
            }
        }
    }
}