package com.example.mvvm_clean.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_clean.data.model.bean.User
import com.example.mvvm_clean.data.model.state.ResultState
import com.example.mvvm_clean.data.repository.LoginRepositoryImpl
import com.example.mvvm_clean.domain.usercase.LoginUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val loginUseCase: LoginUseCase by lazy {
        LoginUseCase()
    }

    private val _loginFlow = MutableStateFlow<UiState<User>>(UiState.Idle)
    val loginFlow: StateFlow<UiState<User>> get() = _loginFlow.asStateFlow()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginFlow.value = UiState.Loading
            loginUseCase.login(username, password).collect {
                when (it) {
                    is ResultState.Success -> {
                        _loginFlow.value = UiState.Success(it.data)
                    }

                    is ResultState.Error -> {
                        _loginFlow.value = UiState.Error(it.errCode, it.errMsg)
                    }
                }
            }
        }
    }
}