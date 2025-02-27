package com.example.mvvm_clean.domain.usercase

import com.example.mvvm_clean.data.model.bean.User
import com.example.mvvm_clean.data.model.state.ResultState
import com.example.mvvm_clean.data.repository.LoginRepositoryImpl
import com.example.mvvm_clean.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val repository: LoginRepository = LoginRepositoryImpl()) {
    fun login(username: String, password: String): Flow<ResultState<User>> {
        return repository.login(username, password)
    }
}