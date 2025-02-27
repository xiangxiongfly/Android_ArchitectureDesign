package com.example.mvvm_clean.data.repository

import com.example.common.http.exceptions.ExceptionHandler
import com.example.mvvm_clean.data.datasource.remote.LoginRemoteDataSource
import com.example.mvvm_clean.data.model.bean.User
import com.example.mvvm_clean.data.model.state.ResultState
import com.example.mvvm_clean.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepositoryImpl(private val loginRemote: LoginRemoteDataSource = LoginRemoteDataSource) :
    LoginRepository {
    override fun login(username: String, password: String): Flow<ResultState<User>> {
        return flow<ResultState<User>> {
            val rep = loginRemote.login(username, password)
            if (rep.isSuccessful()) {
                emit(ResultState.Success(rep.data!!))
            } else {
                emit(ResultState.Error(rep.errorCode, rep.errorMsg ?: "未知错误"))
            }
        }.catch {
            val e = ExceptionHandler.handleException(it)
            emit(ResultState.Error(e.errCode, e.errMsg))
        }.flowOn(Dispatchers.IO)
    }
}