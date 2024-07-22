package com.example.mvvm_coroutines_retrofit_flow_hilt.network.exceptions

import android.net.ParseException
import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

/**
 * 异常处理类
 */
object ExceptionHandler {
    fun handleException(e: Throwable): ApiException {
        return if (e is ServerException) {
            ApiException(e, e.code, e.msg)
        } else if (e is HttpException) {
            ApiException(e, ApiException.Error.HTTP_ERROR, "HTTP错误")
        } else if (e is JSONException || e is ParseException || e is JsonParseException) {
            ApiException(e, ApiException.Error.PARSE_ERROR, "解析错误")
        } else if (e is ConnectException) {
            ApiException(e, ApiException.Error.NETWORD_ERROR, "网络连接失败,请稍后重试")
        } else if (e is UnknownHostException) {
            ApiException(e, ApiException.Error.NETWORD_ERROR, "网络连接失败,请稍后重试")
        } else if (e is InterruptedIOException) {
            ApiException(e, ApiException.Error.CONNECT_TIMEOUT, "连接超时")
        } else if (e is SSLException) {
            ApiException(e, ApiException.Error.SSL_ERROR, "证书验证失败")
        } else {
            ApiException(e, ApiException.Error.UNKNOWN, "网络连接异常,请稍后重试")
        }
    }
}