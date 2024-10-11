package com.example.common.network.exceptions

/**
 * 服务端返回错误码
 */
class ServerException(val code: Int, val msg: String) : RuntimeException()