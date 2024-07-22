package com.example.mvvm.network;

public interface HttpCallback<T> {
    void onError(int errCode, String errMsg);

    void onSuccess(T data);
}
