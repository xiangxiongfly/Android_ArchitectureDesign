package com.example.mvvm_jetpack.data.state

sealed class ResultState<out T> {
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val errCode: Int, val errMsg: String) : ResultState<Nothing>()
}
