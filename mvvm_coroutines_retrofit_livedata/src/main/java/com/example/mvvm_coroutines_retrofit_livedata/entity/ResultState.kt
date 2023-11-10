package com.example.mvvm_coroutines_retrofit_livedata.entity

import com.example.mvvm_coroutines_retrofit_livedata.http.exceptions.ApiException

/**
 * 状态类
 */
sealed class ResultState {
    // 成功状态
    data class Success<out T>(val data: T) : ResultState()

    // 失败状态
    data class Error(val exception: ApiException, val message: String) : ResultState()

    // 加载状态
    object Loading : ResultState()
}