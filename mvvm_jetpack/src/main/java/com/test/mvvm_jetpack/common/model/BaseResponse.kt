package com.test.mvvm_jetpack.common.model

data class BaseResponse<out T>(
    val errorMsg: String,
    val errorCode: Int,
    val data: T
) {
    fun isSuccessful() = errorCode == 0
}