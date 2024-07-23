package com.example.mvvm_coroutines_retrofit_flow.viewmodel

import com.example.mvvm_coroutines_retrofit_flow.base.BaseViewModel
import com.example.mvvm_coroutines_retrofit_flow.entity.bean.User
import com.example.mvvm_coroutines_retrofit_flow.entity.state.ResultState
import com.example.mvvm_coroutines_retrofit_flow.model.LoginModel
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
        launchMain {
            apiCall { loginModel.login(username, password) }.collect {
                _userFlow.value = it
            }
        }
    }
}