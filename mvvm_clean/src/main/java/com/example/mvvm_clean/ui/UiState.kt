package com.example.mvvm_clean.ui

import com.example.mvvm_clean.data.datasource.DataSourceType

/**
 * UI状态类
 */
sealed class UiState<out T> {
    // 成功状态
    data class Success<out T>(
        val data: T,
        val dataSourceType: DataSourceType = DataSourceType.NETWORK
    ) : UiState<T>()

    // 失败状态
    data class Error(val errCode: Int, val errMsg: String) : UiState<Nothing>()

    // 加载状态
    object Loading : UiState<Nothing>()

    // 空闲状态
    object Idle : UiState<Nothing>()
}