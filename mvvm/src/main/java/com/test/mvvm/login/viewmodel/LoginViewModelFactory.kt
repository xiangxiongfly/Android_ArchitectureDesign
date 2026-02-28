package com.test.mvvm.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.mvvm.login.data.LoginRepo

class LoginViewModelFactory(private val repo: LoginRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}