package com.example.mvvm_coroutines_retrofit_flow_hilt.viewmodel

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseViewModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.LoginModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.ResultState
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.UserBean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * ViewModel层
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginModel: LoginModel) : BaseViewModel() {

    private val _userFlow = MutableStateFlow<ResultState<UserBean>>(ResultState.None)
    val userFlow: StateFlow<ResultState<UserBean>> get() = _userFlow.asStateFlow()

    fun login(username: String, password: String) {
        launchIO {
            loginModel.login(username, password).collect {
                _userFlow.value = it
            }
        }
    }
}