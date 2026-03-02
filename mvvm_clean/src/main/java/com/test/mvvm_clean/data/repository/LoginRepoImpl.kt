package com.test.mvvm_clean.data.repository

import com.test.mvvm_clean.common.http.exceptions.ExceptionHandler
import com.test.mvvm_clean.data.repository.remote.LoginApi
import com.test.mvvm_clean.domain.repository.LoginRepo
import com.test.mvvm_clean.data.model.state.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepoImpl(private val loginApi: LoginApi) : LoginRepo {
    override fun login(username: String, password: String) = flow {
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