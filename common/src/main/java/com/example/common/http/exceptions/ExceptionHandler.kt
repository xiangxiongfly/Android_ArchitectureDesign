package com.example.common.http.exceptions

import android.content.Context
import android.net.ConnectivityManager
import android.net.ParseException
import com.example.common.base.BaseApplication
import com.example.common.http.code.ErrorCode
import com.google.gson.JsonParseException
import com.google.gson.stream.MalformedJsonException
import org.apache.http.conn.ConnectTimeoutException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException
import kotlin.contracts.InvocationKind

/**
 * 网络请求错误处理
 */
object ExceptionHandler {
    fun handleException(e: Throwable): com.example.common.http.exceptions.HttpException {
        return when (e) {
            is ServerException -> {
                HttpException(e.code, e.msg)
            }

            is HttpException -> {
                HttpException(ErrorCode.HTTP_ERROR, "服务器连接失败${e.code()}")
            }

            is JsonParseException, is JSONException, is ParseException -> {
                HttpException(ErrorCode.PARSE_ERROR, "解析错误")
            }

            is ConnectException, is MalformedJsonException -> {
                HttpException(ErrorCode.NETWORD_ERROR, "网络连接失败,请稍后重试")
            }

            is SocketTimeoutException, is java.net.SocketException, is ConnectTimeoutException -> {
                HttpException(ErrorCode.TIMEOUT_ERROR, "连接超时")
            }

            is SSLHandshakeException -> {
                HttpException(ErrorCode.SSL_ERROR, "证书验证失败")
            }

            is UnknownHostException -> {
                val activeNetworkInfo = (BaseApplication.getInstance()
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    HttpException(ErrorCode.NETWORD_ERROR, "网络连接失败,请稍后重试")
                } else {
                    HttpException(ErrorCode.NOT_NETWORK_ERROR, "当前无网络,请检查网络设置")
                }
            }

            else -> {
                HttpException(ErrorCode.UNKNOWN, "网络连接异常,请稍后重试")
            }
        }
    }
}