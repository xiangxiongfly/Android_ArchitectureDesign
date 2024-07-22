package com.example.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
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

    public LiveData<ResultState<User>> getLoginLiveData() {
        return loginLiveData;
    }

    public void login(String username, String password) {
        loginLiveData.postValue(new ResultState<>(ResultState.LOADING));
        loginModel.login(username, password, new LoginModel.OnLoginCallback() {
            @Override
            public void onLoginSuccess(User user) {
                loginLiveData.postValue(new ResultState<>(ResultState.SUCCESS, user));
            }

            @Override
            public void onLoginError(int errCode, String errMsg) {
                loginLiveData.postValue(new ResultState<>(ResultState.ERROR, errCode, errMsg));
            }
        });
    }
}
