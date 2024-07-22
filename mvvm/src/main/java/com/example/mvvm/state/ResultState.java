package com.example.mvvm.state;

public class ResultState<T> {
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    public static final int LOADING = 0;
    public static final int COMPLETE = 3;

    private int state = LOADING; // 加载状态
    private int errCode = -1; // 失败状态码
    private String errMsg; // 失败消息
    private T data; // 成功数据

    public ResultState(int state) {
        this.state = state;
    }

    public ResultState(int state, int errCode, String errMsg) {
        this.state = state;
        this.errCode = errCode;
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

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }
}
