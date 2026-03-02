package com.test.mvvm_jetpack.login.viewmodel

import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mvvm_jetpack.common.model.state.ResultState
import com.test.mvvm_jetpack.common.model.state.UiState
import com.test.mvvm_jetpack.login.data.LoginRepo
import com.test.mvvm_jetpack.login.data.model.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: LoginRepo) : ViewModel() {
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
            repo.login(username, password).collect {
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