package com.example.mvvm_clean.data.repository

import com.example.mvvm_clean.base.BaseRepository
import com.example.mvvm_clean.data.datasource.remote.LoginRemoteDataSource
import com.example.mvvm_clean.data.model.bean.User
import com.example.mvvm_clean.data.model.state.ResultState
import com.example.mvvm_clean.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImpl(private val loginRemote: LoginRemoteDataSource = LoginRemoteDataSource) :
    BaseRepository(), LoginRepository {
    override fun login(username: String, password: String): Flow<ResultState<User>> {
        return handleRemoteData { loginRemote.login(username, password) }
    }
}

