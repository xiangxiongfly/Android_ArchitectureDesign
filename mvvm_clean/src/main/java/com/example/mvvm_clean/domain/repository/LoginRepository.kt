package com.example.mvvm_clean.domain.repository

import com.example.mvvm_clean.data.model.bean.User
import com.example.mvvm_clean.data.model.state.ResultState
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun login(username: String, password: String): Flow<ResultState<User>>
}