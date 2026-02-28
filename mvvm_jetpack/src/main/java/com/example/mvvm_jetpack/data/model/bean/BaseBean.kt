package com.example.mvvm_jetpack.data.model.bean

data class BaseBean<T>(
    val errorCode: Int = 0,
    var errorMsg: String = "",
    val data: T? = null
) {
    fun isSuccessful() = errorCode == 0
}