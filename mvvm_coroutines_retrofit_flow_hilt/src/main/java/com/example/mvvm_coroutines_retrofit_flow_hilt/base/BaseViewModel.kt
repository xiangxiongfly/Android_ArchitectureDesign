package com.example.mvvm_coroutines_retrofit_flow_hilt.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.network.exceptions.ExceptionHandler
import com.example.common.network.exceptions.ServerException
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.BeanFactory
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.state.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
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

    suspend fun <T> apiCallFlow(block: suspend () -> BeanFactory<T>): Flow<UiState<T>> {
        return flow<UiState<T>> {
            val response = block()
            if (response.isSuccessful()) {
                emit(UiState.Success(response.data!!))
            } else {
                val serverException = ServerException(response.errorCode, response.errorMsg!!)
                val e = ExceptionHandler.handleException(serverException)
                emit(UiState.Error(e, e.errMsg))
            }
        }.flowOn(Dispatchers.IO)
            .catch {
                val e = ExceptionHandler.handleException(it)
                emit(UiState.Error(e, e.errMsg))
            }
    }

    suspend fun <T> apiCall(block: suspend () -> BeanFactory<T>): UiState<T> {
        return withContext(Dispatchers.IO) {
            try {
                val response = block()
                if (response.isSuccessful()) {
                    UiState.Success(response.data!!)
                } else {
                    val serverException = ServerException(response.errorCode, response.errorMsg!!)
                    val e = ExceptionHandler.handleException(serverException)
                    UiState.Error(e, e.errMsg)
                }
            } catch (e: Exception) {
                val e = ExceptionHandler.handleException(e)
                UiState.Error(e, e.errMsg)
            }
        }
    }


}