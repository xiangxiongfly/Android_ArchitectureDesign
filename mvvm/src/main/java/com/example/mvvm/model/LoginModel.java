package com.example.mvvm.model;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.bean.BaseResponse;
import com.example.mvvm.bean.User;
import com.example.mvvm.state.ResultState;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModel {
    public void login(MutableLiveData<ResultState<User>> liveData, String username, String password) {
        liveData.postValue(new ResultState(ResultState.STATE_LOADING));
        final String url = "https://www.wanandroid.com/user/login";
        OkHttpClient client = new OkHttpClient();
        FormBody body = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    BaseResponse<User> userResponse = new Gson().fromJson(json, new TypeToken<BaseResponse<User>>() {
                    }.getType());
                    if (userResponse.isSuccessful()) {
                        liveData.postValue(new ResultState(ResultState.STATE_SUCCESS, userResponse.getData()));
                    } else {
                        liveData.postValue(new ResultState(ResultState.STATE_ERROR, userResponse.getErrorMsg()));
                    }
                } else {
                    liveData.postValue(new ResultState(ResultState.STATE_ERROR, "网络连接失败：${response.code}"));
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                liveData.postValue(new ResultState(ResultState.STATE_ERROR, "网络连接失败：${e.message}"));
            }
        });
    }
}
