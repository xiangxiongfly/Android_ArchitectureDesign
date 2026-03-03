package com.example.mvi.domain.usecase

import com.example.mvi.domain.repository.LoginRepo
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repo: LoginRepo) {
    fun login(username: String, password: String) = repo.login(username, password)
}