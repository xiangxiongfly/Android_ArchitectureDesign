package com.example.mvvm_clean.domain

import com.example.mvvm_clean.data.entity.bean.User
import com.example.mvvm_clean.data.entity.state.UiState
import com.example.mvvm_clean.data.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val repository: LoginRepository = LoginRepository()) {
    suspend fun login(username: String, password: String): Flow<UiState<User>> {
        return repository.login(username, password)
    }
}