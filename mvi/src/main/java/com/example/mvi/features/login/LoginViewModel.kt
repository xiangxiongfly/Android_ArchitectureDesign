package com.example.mvi.features.login

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvi.data.model.bean.Login
import com.example.mvi.data.model.state.ResultState
import com.example.mvi.data.model.state.UiState
import com.example.mvi.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _loginState = MutableLiveData<UiState<Login>>(UiState.Idle)
    val loginState: LiveData<UiState<Login>> get() = _loginState

    fun processIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.Login -> {
                handleLogin(intent.username, intent.password)
            }

            is LoginIntent.ResetState -> {
                _loginState.value = UiState.Idle
            }
        }
    }

    private fun handleLogin(username: String, password: String) {
        if (TextUtils.isEmpty(username)) {
            _loginState.value = UiState.Error("用户名不能为空")
            return
        }
        if (TextUtils.isEmpty(password)) {
            _loginState.value = UiState.Error("密码不能为空")
            return
        }
        _loginState.value = UiState.Loading
        viewModelScope.launch {
            loginUseCase.login(username, password).collect {
                when (it) {
                    is ResultState.Success -> {
                        _loginState.value = UiState.Success(it.data)
                    }

                    is ResultState.Error -> {
                        _loginState.value = UiState.Error(it.errMsg, it.errCode)
                    }
                }
            }
        }
    }
}