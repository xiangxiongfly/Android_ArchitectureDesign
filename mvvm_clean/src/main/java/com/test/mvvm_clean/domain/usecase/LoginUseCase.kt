package com.test.mvvm_clean.domain.usecase

import com.test.mvvm_clean.domain.repository.LoginRepo
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repo: LoginRepo) {
    fun login(username: String, password: String) = repo.login(username, password)
}