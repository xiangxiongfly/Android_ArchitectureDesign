package com.example.mvvm_coroutines_retrofit_flow_hilt.ui.login

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseViewModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.UserBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.respository.remote.LoginRepo
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * ViewModel层
 */
@HiltViewModel
class LoginViewModel @Inject constructor(private val loginModel: LoginRepo) : BaseViewModel() {

    private val _userFlow = MutableStateFlow<UiState<UserBean>>(UiState.None)
    val userFlow: StateFlow<UiState<UserBean>> get() = _userFlow.asStateFlow()

    fun login(username: String, password: String) {
        launchMain {
            apiCallFlow { loginModel.login(username, password) }
                .onStart {
                    _userFlow.value = UiState.Loading
                }
                .collect {
                    _userFlow.value = it
                }
        }
    }
}