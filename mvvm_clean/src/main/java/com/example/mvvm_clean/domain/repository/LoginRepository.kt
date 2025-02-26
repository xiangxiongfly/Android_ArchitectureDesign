package com.example.mvvm_clean.domain.repository

import com.example.mvvm_clean.data.model.bean.User
import com.example.mvvm_clean.data.model.state.UiState
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(username: String, password: String): Flow<UiState<User>>
}