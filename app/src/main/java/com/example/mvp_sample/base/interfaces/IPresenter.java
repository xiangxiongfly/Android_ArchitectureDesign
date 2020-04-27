package com.example.mvp_sample.base.interfaces;

public interface IPresenter<V extends IView> {

    /**
     * 绑定View
     */
    void attachView(V view);

    /**
     * 解绑View
     */
    void detachView();

    /**
     * 是否绑定View
     */
    boolean isViewAttached();

    /**
     * 获取View
     */
    V getView();
}
