package com.example.mvvm_flow.entity.bean

data class BaseResponse<T>(
    val errorCode: Int = 0,
    var errorMsg: String = "",
    val data: T? = null
) {
    fun isSuccessful() = errorCode == 0
}