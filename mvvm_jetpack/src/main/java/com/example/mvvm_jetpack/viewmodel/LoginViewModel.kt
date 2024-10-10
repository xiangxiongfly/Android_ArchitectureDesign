package com.example.mvvm_jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_jetpack.entity.bean.User
import com.example.mvvm_jetpack.entity.state.UiState
import com.example.mvvm_jetpack.repository.LoginRepository
import com.example.common.utils.livedata.SingleLiveData
import kotlinx.coroutines.launch

/**
 * ViewModel层
 */
class LoginViewModel : ViewModel() {
    private val _loginLiveData = SingleLiveData<UiState<User>>()
    val loginLiveData: SingleLiveData<UiState<User>> get() = _loginLiveData

    fun login(username: String, password: String) {
        _loginLiveData.value = UiState.Loading
        viewModelScope.launch {
            val loginRepo = LoginRepository()
            val resultState = loginRepo.login(username, password)
            _loginLiveData.value = resultState
        }
    }
}