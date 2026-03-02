package com.test.mvvm_jetpack.common.model.state

sealed class ResultState<out T> {
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val errMsg: String, val errCode: Int? = 0) : ResultState<Nothing>()
}