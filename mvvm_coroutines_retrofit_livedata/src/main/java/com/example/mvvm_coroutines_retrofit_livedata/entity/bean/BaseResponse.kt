package com.example.mvvm_coroutines_retrofit_livedata.entity.bean

data class BaseResponse<T>(
    val errorCode: Int = 0,
    var errorMsg: String = "",
    val data: T? = null
) {
    fun isSuccessful() = errorCode == 0
}