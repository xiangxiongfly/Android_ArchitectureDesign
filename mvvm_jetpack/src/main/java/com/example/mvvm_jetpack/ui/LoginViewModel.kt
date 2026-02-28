package com.example.mvvm_jetpack.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.common.utils.livedata.SingleLiveData
import com.example.mvvm_jetpack.data.model.bean.User
import com.example.mvvm_jetpack.data.repository.LoginRepository
import com.example.mvvm_jetpack.data.state.ResultState
import com.example.mvvm_jetpack.ui.state.UiState
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val loginRepo: LoginRepository by lazy { LoginRepository() }
    private val _loginState = SingleLiveData<UiState<User>>()
    val loginState: LiveData<UiState<User>> = _loginState

    fun login(username: String, password: String) {
        _loginState.value = UiState.Loading
        viewModelScope.launch {
            when (val resultState = loginRepo.login(username, password)) {
                is ResultState.Success -> {
                    _loginState.value = UiState.Success(resultState.data)
                }
                is ResultState.Error -> {
                    _loginState.value = UiState.Error(resultState.errCode, resultState.errMsg)
                }
            }
        }
    }
}