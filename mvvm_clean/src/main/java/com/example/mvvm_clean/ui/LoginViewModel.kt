package com.example.mvvm_clean.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_clean.data.entity.bean.User
import com.example.mvvm_clean.data.entity.state.UiState
import com.example.mvvm_clean.data.repository.LoginRepository
import com.example.mvvm_clean.domain.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginRepository: LoginRepository by lazy {
        LoginRepository()
    }

    private val loginUseCase: LoginUseCase by lazy {
        LoginUseCase()
    }

    private val _loginFlow = MutableStateFlow<UiState<User>>(UiState.Idle)
    val loginFlow: StateFlow<UiState<User>> get() = _loginFlow.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginFlow.value = UiState.Loading

//            loginRepository.login(username, password).collect {
//                _loginFlow.value = it
//            }

            loginUseCase.login(username, password).collect {
                _loginFlow.value = it
            }
        }
    }
}