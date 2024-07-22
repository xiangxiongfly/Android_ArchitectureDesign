package com.example.mvvm.model;

import com.example.mvvm.bean.User;
import com.example.mvvm.network.HttpCallback;
import com.example.mvvm.network.HttpManager;
import com.example.mvvm.network.RequestParams;

public class LoginModel {
    public void login(String username, String password, OnLoginCallback callback) {
        final String url = "https://www.wanandroid.com/user/login";
        RequestParams requestParams = new RequestParams();
        requestParams.put("username", username)
                .put("password", password);
        HttpManager.<User>post(url, requestParams, new HttpCallback<User>() {
            @Override
            public void onError(int errCode, String errMsg) {
                callback.onLoginError(errCode, errMsg);
            }

            @Override
            public void onSuccess(User user) {
                callback.onLoginSuccess(user);
            }
        });
    }

    public interface OnLoginCallback {
        void onLoginSuccess(User user);

        void onLoginError(int errCode, String errMsg);
    }
}
