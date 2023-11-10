package com.example.mvvm_flow.viewmodel

import com.example.mvvm_flow.base.BaseViewModel
import com.example.mvvm_flow.entity.ResultState
import com.example.mvvm_flow.entity.bean.User
import com.example.mvvm_flow.model.LoginModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel层
 */
class LoginViewModel : BaseViewModel() {
    private val loginModel: LoginModel by lazy {
        LoginModel()
    }

    private val _userFlow = MutableStateFlow<ResultState<User>>(ResultState.None)
    val userFlow: StateFlow<ResultState<User>> get() = _userFlow.asStateFlow()

    fun login(username: String, password: String) {
        launchWithIO {
            loginModel.login(username, password).collect {
                when (it) {
                    is ResultState.Loading,
                    is ResultState.Success<User>,
                    is ResultState.Error,
                    is ResultState.Complete -> _userFlow.value = it
                    else -> {
                    }
                }
            }
        }
    }
}