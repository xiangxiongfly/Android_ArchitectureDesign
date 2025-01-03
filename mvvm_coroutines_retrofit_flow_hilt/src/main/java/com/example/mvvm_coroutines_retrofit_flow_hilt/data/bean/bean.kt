package com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean

open class BaseBean {
    val errorCode: Int = 0
    val errorMsg: String? = null

    fun isSuccessful() = errorCode == 0
}

class BeanFactory<T> : BaseBean() {
    var data: T? = null
}