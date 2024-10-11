package com.example.mvvm_clean.data.entity.state

import com.example.common.network.exceptions.ApiException

/**
 * UI状态类
 */
sealed class UiState<out R> {
    // 成功状态
    data class Success<out T>(val data: T) : UiState<T>()

    // 失败状态
    data class Error(val exception: ApiException, val message: String) : UiState<Nothing>()

    // 加载状态
    object Loading : UiState<Nothing>()

    // 空闲状态
    object Idle : UiState<Nothing>()
}