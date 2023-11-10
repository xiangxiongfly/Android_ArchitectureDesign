package com.example.mvvm_coroutines_retrofit_livedata.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.mvvm_coroutines_retrofit_livedata.base.BaseViewModel
import com.example.mvvm_coroutines_retrofit_livedata.entity.ResultState
import com.example.mvvm_coroutines_retrofit_livedata.model.LoginModel

class LoginViewModel : BaseViewModel() {
    private val _loginLiveData = MutableLiveData<ResultState>()
    val loginLiveData get() = _loginLiveData

    fun login(username: String, password: String) {
        launchWithIO {
            val loginModel = LoginModel()
            val resultState = loginModel.login(username, password)
            loginLiveData.postValue(resultState)
        }
    }
}