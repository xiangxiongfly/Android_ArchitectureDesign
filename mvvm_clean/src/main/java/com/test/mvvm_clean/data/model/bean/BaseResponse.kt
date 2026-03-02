package com.test.mvvm_clean.data.model.bean

data class BaseResponse<out T>(
    val errorMsg: String,
    val errorCode: Int,
    val data: T
) {
    fun isSuccessful() = errorCode == 0
}