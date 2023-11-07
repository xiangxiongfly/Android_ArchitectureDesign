package com.example.mvp_rxjava_retrofit.http;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

/**
 * 异常处理类
 */
public class ExceptionHandler {

    /**
     * 处理异常信息：
     */
    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof ServerException) { //服务端返回错误
            ServerException serverException = (ServerException) e;
            ex = new ApiException(e, serverException.getCode());
            ex.setDisplayMessage(serverException.getMessage());
            return ex;
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ApiException.Error.HTTP_ERROR);
            switch (httpException.code()) {
                case ApiException.Http.UNAUTHORIZED:
                case ApiException.Http.FORBIDDEN:
                case ApiException.Http.NOT_FOUND:
                case ApiException.Http.REQUEST_TIMEOUT:
                case ApiException.Http.GATEWAY_TIMEOUT:
                case ApiException.Http.INTERNAL_SERVER_ERROR:
                case ApiException.Http.BAD_GATEWAY:
                case ApiException.Http.SERVICE_UNAVAILABLE:
                default:
                    ex.setDisplayMessage("网络错误");
                    break;
            }
            return ex;
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            ex = new ApiException(e, ApiException.Error.PARSE_ERROR);
            ex.setDisplayMessage("解析错误");
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, ApiException.Error.NETWORD_ERROR);
            ex.setDisplayMessage("网络连接失败,请稍后重试");
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ApiException(e, ApiException.Error.SSL_ERROR);
            ex.setDisplayMessage("证书验证失败");
            return ex;
        } else {
            ex = new ApiException(e, ApiException.Error.UNKNOWN);
            ex.setDisplayMessage("网络连接异常,请稍后重试");
            return ex;
        }
    }
}
