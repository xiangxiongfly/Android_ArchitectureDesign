package com.example.mvvm_jetpack.entity.state

/**
 * 状态类
 */
sealed class UiState<out Any> {
    // 成功状态
    data class Success<out T : Any>(val data: T) : UiState<T>()

    // 失败状态
    data class Error(val errCode: Int, val errMsg: String) : UiState<Nothing>()

    // 加载状态
    object Loading : UiState<Nothing>()
}