package com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean

data class BaseResponse<T>(
    val errorCode: Int = 0,
    val errorMsg: String = "",
    val data: T? = null
) {
    fun isSuccessful() = errorCode == 0
}