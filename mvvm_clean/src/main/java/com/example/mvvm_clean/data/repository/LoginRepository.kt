package com.example.mvvm_clean.data.repository

import com.example.common.network.exceptions.ExceptionHandler
import com.example.common.network.exceptions.ServerException
import com.example.mvvm_clean.data.datasource.remote.LoginRemoteDataSource
import com.example.mvvm_clean.data.entity.bean.User
import com.example.mvvm_clean.data.entity.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LoginRepository(private val remoteData: LoginRemoteDataSource = LoginRemoteDataSource) {

    suspend fun login(username: String, password: String): Flow<UiState<User>> {
        return flow<UiState<User>> {
            val userResponse = remoteData.login(username, password)
            if (userResponse.isSuccessful()) {
                emit(UiState.Success(userResponse.data!!))
            } else {
                val serverException = ServerException(userResponse.errorCode, userResponse.errorMsg)
                val e = ExceptionHandler.handleException(serverException)
                emit(UiState.Error(e, e.errMsg))
            }
        }.flowOn(Dispatchers.IO)
    }
}