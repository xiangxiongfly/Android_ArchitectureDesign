package com.example.mvi.domain.repository

import com.example.mvi.data.model.bean.Login
import com.example.mvi.data.model.state.ResultState
import kotlinx.coroutines.flow.Flow

interface LoginRepo {
    fun login(username: String, password: String): Flow<ResultState<Login>>
}