package com.example.mvvm_jetpack.ui.state

/**
 * 状态类
 */
sealed class UiState<out T> {
    // 空闲状态
    object Idle : UiState<Nothing>()

    // 加载状态
    object Loading : UiState<Nothing>()

    // 成功状态
    data class Success<out T>(val data: T) : UiState<T>()

    // 失败状态
    data class Error(val errCode: Int, val errMsg: String) : UiState<Nothing>()
}