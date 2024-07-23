package com.example.mvvm_coroutines_retrofit_livedata.viewmodel

import com.example.mvvm_coroutines_retrofit_livedata.base.BaseViewModel
import com.example.mvvm_coroutines_retrofit_livedata.entity.state.ResultState
import com.example.mvvm_coroutines_retrofit_livedata.livedata.SingleLiveData
import com.example.mvvm_coroutines_retrofit_livedata.model.LoginModel

/**
 * ViewModel层
 */
class LoginViewModel : BaseViewModel() {
    private val _loginLiveData = SingleLiveData<ResultState>()
    val loginLiveData: SingleLiveData<ResultState> get() = _loginLiveData

    fun login(username: String, password: String) {
        _loginLiveData.value = ResultState.Loading
        launchIO {
            val loginModel = LoginModel()
            val resultState = apiCall {
                loginModel.login(username, password)
            }
            _loginLiveData.postValue(resultState)
        }
    }
}