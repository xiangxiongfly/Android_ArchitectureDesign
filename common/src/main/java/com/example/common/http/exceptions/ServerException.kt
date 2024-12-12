package com.example.common.http.exceptions

import java.lang.Throwable

/**
 * 服务端返回错误码
 */
class ServerException(val code: Int, val msg: String) : RuntimeException()