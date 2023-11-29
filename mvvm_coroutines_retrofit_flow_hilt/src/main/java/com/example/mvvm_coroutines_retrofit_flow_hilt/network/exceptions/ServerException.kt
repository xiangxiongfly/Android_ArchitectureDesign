package com.example.mvvm_coroutines_retrofit_flow_hilt.network.exceptions

/**
 * 服务端返回错误码
 */
class ServerException(val code: Int, val msg: String) : RuntimeException()