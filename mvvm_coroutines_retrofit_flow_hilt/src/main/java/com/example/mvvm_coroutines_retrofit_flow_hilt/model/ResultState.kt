package com.example.mvvm_coroutines_retrofit_flow_hilt.model

import com.example.mvvm_coroutines_retrofit_flow_hilt.network.exceptions.ApiException

/**
 * 状态类
 */
sealed class ResultState<out R> {
    // 成功状态
    data class Success<out T>(val data: T) : ResultState<T>()

    // 失败状态
    data class Error(val exception: ApiException, val message: String) : ResultState<Nothing>()

    // 加载中状态
    object Loading : ResultState<Nothing>()

    // 完成状态
    object Complete : ResultState<Nothing>()

    // 无状态
    object None : ResultState<Nothing>()
}