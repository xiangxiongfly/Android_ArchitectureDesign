package com.test.mvvm_jetpack.login.data

import com.test.mvvm_jetpack.common.http.exceptions.ExceptionHandler
import com.test.mvvm_jetpack.common.model.state.ResultState
import com.test.mvvm_jetpack.login.data.api.LoginApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepo(private val loginApi: LoginApi) {
    fun login(username: String, password: String) = flow {
        val res = loginApi.login(username, password)
        if (res.isSuccessful()) {
            emit(ResultState.Success(res.data))
        } else {
            emit(ResultState.Error(res.errorMsg, res.errorCode))
        }
    }.catch {
        val e = ExceptionHandler.handleException(it)
        emit(ResultState.Error(e.errMsg, e.errCode))
    }.flowOn(Dispatchers.IO)
}