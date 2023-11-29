package com.example.mvvm_coroutines_retrofit_flow_hilt.viewmodel

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseViewModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.entity.ResultState
import com.example.mvvm_coroutines_retrofit_flow_hilt.entity.bean.User
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.LoginModel
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