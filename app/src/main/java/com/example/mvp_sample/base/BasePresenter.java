package com.example.mvp_sample.base;

import com.example.mvp_sample.base.interfaces.IPresenter;
import com.example.mvp_sample.base.interfaces.IView;

import java.lang.ref.WeakReference;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    private WeakReference<V> mRef;

    @Override
    public void attachView(V view) {
        mRef = new WeakReference<>(view);
    }

    @Override
    public void detachView() {
        if (mRef != null) {
            mRef.clear();
            mRef = null;
        }
    }

    @Override
    public boolean isViewAttached() {
        return mRef != null && mRef.get() != null;
    }

    @Override
    public V getView() {
        return mRef.get();
    }
}
