package com.example.mvvm.state;

public class ResultState<T> {
    public static final int STATE_SUCCESS = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_LOADING = 0;
    public static final int STATE_COMPLETE = 3;

    private int state = STATE_LOADING;
    private String errMsg;
    private T data;

    public ResultState(int state) {
        this.state = state;
    }

    public ResultState(int state, String errMsg) {
        this.state = state;
        this.errMsg = errMsg;
    }

    public ResultState(int state, T data) {
        this.state = state;
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
