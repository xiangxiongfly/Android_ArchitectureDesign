package com.example.mvp_rxjava_retrofit.http;

/**
 * 自定义异常
 */
public class ApiException {
    private Throwable e;
    private int code;
    private String message;
    private int statusCode;

    public ApiException(Throwable e, int code, String message) {
        this.e = e;
        this.code = code;
        this.message = message;
    }

    public ApiException(Throwable e, int code, String message, int statusCode) {
        this.e = e;
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    public Throwable getThrowable() {
        return e;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    /**
     * 异常状态码
     */
    public static final class Error {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;
        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;
        /**
         * 无网络异常
         */
        public static final int NOT_NETWORK_ERROR = 1006;
        /**
         * 连接超时异常
         */
        public static final int CONNECT_TIMEOUT_ERROR = 1007;
        /**
         * Http状态码异常
         */
        public static final int HTTP_STATUS_ERROR = 1008;
    }
}
