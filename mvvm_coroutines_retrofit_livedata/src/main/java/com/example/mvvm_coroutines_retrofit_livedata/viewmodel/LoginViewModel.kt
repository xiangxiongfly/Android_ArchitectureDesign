package com.example.mvvm_coroutines_retrofit_livedata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_coroutines_retrofit_livedata.base.BaseViewModel
import com.example.mvvm_coroutines_retrofit_livedata.entity.ResultState
import com.example.mvvm_coroutines_retrofit_livedata.model.LoginModel

/**
 * ViewModel层
 */
class LoginViewModel : BaseViewModel() {
    private val _loginLiveData = MutableLiveData<ResultState>()
    val loginLiveData: LiveData<ResultState> get() = _loginLiveData

    fun login(username: String, password: String) {
        _loginLiveData.value = ResultState.Loading
        launchWithIO {
            val loginModel = LoginModel()
            val resultState = loginModel.login(username, password)
            _loginLiveData.postValue(resultState)
        }
    }
}