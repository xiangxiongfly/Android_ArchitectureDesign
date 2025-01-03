package com.example.mvvm_clean.data.repository

import com.example.common.http.exceptions.ExceptionHandler
import com.example.common.http.exceptions.ServerException
import com.example.mvvm_clean.data.datasource.remote.LoginRemoteDataSource
import com.example.mvvm_clean.data.entity.bean.User
import com.example.mvvm_clean.data.entity.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepository(private val loginRemote: LoginRemoteDataSource = LoginRemoteDataSource) {
    suspend fun login(username: String, password: String): Flow<UiState<User>> {
        return flow<UiState<User>> {
            val userResponse = loginRemote.login(username, password)
            if (userResponse.isSuccessful()) {
                emit(UiState.Success(userResponse.data!!))
            } else {
                val serverException = ServerException(userResponse.errorCode, userResponse.errorMsg)
                val e = ExceptionHandler.handleException(serverException)
                emit(UiState.Error(e, e.errMsg))
            }
        }.catch {
            val e = ExceptionHandler.handleException(it)
            emit(UiState.Error(e, e.errMsg))
        }.flowOn(Dispatchers.IO)
    }
}