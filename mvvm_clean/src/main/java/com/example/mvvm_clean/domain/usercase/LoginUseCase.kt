package com.example.mvvm_clean.domain.usercase

import com.example.mvvm_clean.data.model.bean.User
import com.example.mvvm_clean.data.model.state.UiState
import com.example.mvvm_clean.data.repository.LoginRepositoryImpl
import com.example.mvvm_clean.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val repository: LoginRepository = LoginRepositoryImpl()) {
    suspend fun login(username: String, password: String): Flow<UiState<User>> {
        return repository.login(username, password)
    }
}