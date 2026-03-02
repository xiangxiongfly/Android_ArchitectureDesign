package com.test.mvvm.login.viewmodel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mvvm.common.model.state.ResultState
import com.test.mvvm.common.model.state.UiState
import com.test.mvvm.login.data.LoginRepo
import com.test.mvvm.login.data.model.Login
import kotlinx.coroutines.launch

class LoginViewModel(private val repo: LoginRepo) : ViewModel() {
    private val _loginState = MutableLiveData<UiState<Login>>(UiState.Idle)
    val loginState: LiveData<UiState<Login>> get() = _loginState

    fun login(username: String, password: String) {
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
            val result = repo.login(username, password)
            when (result) {
                is ResultState.Success -> {
                    _loginState.value = UiState.Success(result.data)
                }

                is ResultState.Error -> {
                    _loginState.value = UiState.Error(result.errMsg, result.errCode)
                }
            }
        }
    }
}