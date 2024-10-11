package com.example.mvvm_clean.data.entity.bean

data class BaseBean<T>(
    val errorCode: Int = 0,
    var errorMsg: String = "",
    val data: T? = null
) {
    fun isSuccessful() = errorCode == 0
}