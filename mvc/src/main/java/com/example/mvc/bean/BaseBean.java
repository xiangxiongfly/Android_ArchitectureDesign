package com.example.mvc.bean;

public class BaseBean<T> {
    private String errorMsg;
    private int errorCode;
    private T data;

    public BaseBean() {
    }

    public BaseBean(String errorMsg, int errorCode, T data) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.data = data;
    }

    public boolean isSuccessful() {
        return errorCode == 0;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
