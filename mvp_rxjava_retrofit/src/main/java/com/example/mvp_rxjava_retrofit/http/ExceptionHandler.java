package com.example.mvp_rxjava_retrofit.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;

import androidx.annotation.NonNull;

import com.example.mvp_rxjava_retrofit.base.BaseApp;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import retrofit2.HttpException;

/**
 * 异常处理类
 */
public class ExceptionHandler {

    /**
     * 处理异常信息：
     */
    @NonNull
    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof ServerException) { //服务端返回错误
            ServerException serverException = (ServerException) e;
            ex = new ApiException(e, serverException.getCode(), serverException.getMessage());
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, ApiException.Error.HTTP_ERROR, "网络请求失败" + httpException.code(), httpException.code());
        } else if (e instanceof UnknownHostException) {
            NetworkInfo info = ((ConnectivityManager) BaseApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                ex = new ApiException(e, ApiException.Error.NETWORD_ERROR, "网络连接失败,请稍后重试");
            } else {
                ex = new ApiException(e, ApiException.Error.NOT_NETWORK_ERROR, "当前无网络,请检查网络设置");
            }
        } else if (e instanceof SocketTimeoutException || e instanceof TimeoutException) {
            ex = new ApiException(e, ApiException.Error.CONNECT_TIMEOUT_ERROR, "网络连接超时,请稍后重试");
        } else if (e instanceof JsonParseException ||
                e instanceof JSONException ||
                e instanceof ParseException) {
            ex = new ApiException(e, ApiException.Error.PARSE_ERROR, "数据解析错误");
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, ApiException.Error.NETWORD_ERROR, "网络连接失败,请稍后重试");
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ApiException(e, ApiException.Error.SSL_ERROR, "证书验证失败");
        } else {
            ex = new ApiException(e, ApiException.Error.UNKNOWN, "网络连接异常,请稍后重试");
        }
        return ex;
    }
}
