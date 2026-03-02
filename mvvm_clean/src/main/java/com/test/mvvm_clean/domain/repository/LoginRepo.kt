package com.test.mvvm_clean.domain.repository

import com.test.mvvm_clean.data.model.bean.Login
import com.test.mvvm_clean.data.model.state.ResultState
import kotlinx.coroutines.flow.Flow

interface LoginRepo {
    fun login(username: String, password: String): Flow<ResultState<Login>>
}