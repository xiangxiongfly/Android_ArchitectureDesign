package com.example.mvvm_coroutines_retrofit_flow_hilt.network.exceptions

/**
 * 统一管理异常类
 */
class ApiException(throwable: Throwable, val code: Int, val displayMessage: String) :
    Exception(throwable) {

    /**
     * Http状态码
     */
    object Http {
        const val UNAUTHORIZED = 401
        const val FORBIDDEN = 403
        const val NOT_FOUND = 404
        const val REQUEST_TIMEOUT = 408
        const val INTERNAL_SERVER_ERROR = 500
        const val BAD_GATEWAY = 502
        const val SERVICE_UNAVAILABLE = 503
        const val GATEWAY_TIMEOUT = 504
    }

    /**
     * 异常状态码
     */
    object Error {
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
         * 连接超时
         */
        const val CONNECT_TIMEOUT = 1004

        /**
         * 证书出错
         */
        const val SSL_ERROR = 1005
    }
}