package com.example.mvp_rxjava_retrofit.base;

import com.example.mvp_rxjava_retrofit.bean.BaseResponse;
import com.example.mvp_rxjava_retrofit.http.ApiException;
import com.example.mvp_rxjava_retrofit.http.ExceptionHandler;
import com.example.mvp_rxjava_retrofit.http.ServerException;

import io.reactivex.observers.DisposableObserver;

public abstract class BaseObserver<T> extends DisposableObserver<BaseResponse<T>> {

    public BaseObserver() {
    }

    @Override
    protected void onStart() {
        super.onStart();
        onBegin();
    }

    @Override
    public void onNext(BaseResponse<T> baseResponse) {
        int errorCode = baseResponse.getErrorCode();
        String errorMsg = baseResponse.getErrorMsg();
        if (baseResponse.isSuccessful()) {
            onSuccess(baseResponse.getData());
        } else {
            onError(new ServerException(errorCode, errorMsg));
        }
    }

    @Override
    public void onError(Throwable e) {
        onError(ExceptionHandler.handleException(e));
    }

    @Override
    public void onComplete() {
        onEnd();
    }

    protected void onBegin() {
    }

    protected abstract void onSuccess(T data);

    protected abstract void onError(ApiException e);

    protected void onEnd() {
    }
}
