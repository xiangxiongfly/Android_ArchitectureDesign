package com.example.common.http.exceptions

/**
 * 服务端返回错误码
 */
class ServerException(val code: Int, val msg: String) : RuntimeException()