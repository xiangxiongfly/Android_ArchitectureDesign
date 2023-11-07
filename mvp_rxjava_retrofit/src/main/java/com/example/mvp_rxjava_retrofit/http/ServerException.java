package com.example.mvp_rxjava_retrofit.http;

import androidx.annotation.Nullable;

/**
 * 服务端返回错误类
 */
public class ServerException extends RuntimeException {
    private int code;
    private String message;

    public ServerException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Nullable
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
