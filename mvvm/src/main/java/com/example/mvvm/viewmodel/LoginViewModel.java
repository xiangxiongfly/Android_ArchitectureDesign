package com.example.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.bean.User;
import com.example.mvvm.model.LoginModel;
import com.example.mvvm.state.ResultState;

public class LoginViewModel {
    private MutableLiveData<ResultState<User>> loginLiveData;
    private LoginModel loginModel;

    public LoginViewModel() {
        loginLiveData = new MutableLiveData<>();
        loginModel = new LoginModel();
    }

    public MutableLiveData<ResultState<User>> getLoginLiveData() {
        return loginLiveData;
    }

    public void login(String username, String password) {
        loginModel.login(loginLiveData, username, password);
    }
}
