package com.example.common.http.code

/**
 * 异常状态码
 */
object ErrorCode {
    /**
     * 未知错误
     */
    const val UNKNOWN = 1000

    /**
     * 解析错误
     */
    const val PARSE_ERROR = 1001

    /**
     * 网络错误
     */
    const val NETWORD_ERROR = 1002

    /**
     * 协议出错
     */
    const val HTTP_ERROR = 1003

    /**
     * 证书出错
     */
    const val SSL_ERROR = 1005

    /**
     * 连接超时
     */
    const val TIMEOUT_ERROR = 1006

    /**
     * 无网
     */
    const val NOT_NETWORK_ERROR = 1007
}