package com.example.mvvm_coroutines_retrofit_livedata.http.exceptions

import android.net.ParseException
import com.google.gson.JsonParseException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

object ExceptionHandler {

    fun handleException(e: Throwable): ApiException {
        if (e is ServerException) {
            return ApiException(e, e.code, e.msg)
        } else if (e is HttpException) {
            return ApiException(e, ApiException.Error.HTTP_ERROR, "服务器连接失败")
        } else if (e is JsonParseException || e is JSONException || e is ParseException) {
            return ApiException(e, ApiException.Error.PARSE_ERROR, "解析错误")
        } else if (e is ConnectException || e is ConnectTimeoutException || e is SocketTimeoutException) {
            return ApiException(e, ApiException.Error.NETWORD_ERROR, "网络连接失败,请稍后重试")
        } else if (e is SSLHandshakeException) {
            return ApiException(e, ApiException.Error.SSL_ERROR, "证书验证失败")
        } else {
            return ApiException(e, ApiException.Error.UNKNOWN, "网络连接异常,请稍后重试")
        }
    }
}