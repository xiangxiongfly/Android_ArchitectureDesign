package com.test.mvvm_jetpack.common.model.state

sealed class ListState<out T> {
    // 初始状态
    object Idle : ListState<Nothing>()

    // 初始加载中
    object Loading : ListState<Nothing>()

    // 下拉刷新中
    object Refreshing : ListState<Nothing>()

    // 上拉加载中
    object LoadingMore : ListState<Nothing>()

    // 加载成功
    data class Success<T>(val items: List<T>) : ListState<T>()

    // 刷新成功
    data class RefreshSuccess<T>(val items: List<T>) : ListState<T>()

    // 加载成功，只包含新加载的数据
    data class LoadMoreSuccess<T>(val items: List<T>) : ListState<T>()

    // 空数据
    object Empty : ListState<Nothing>()

    // 失败状态
    data class Error(val errMsg: String) : ListState<Nothing>()
}