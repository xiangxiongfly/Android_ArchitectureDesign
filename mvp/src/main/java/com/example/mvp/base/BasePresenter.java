package com.example.mvp.base;

import java.lang.ref.WeakReference;

public class BasePresenter<V> {
    private WeakReference<V> mRef;

    public void attachView(V view) {
        mRef = new WeakReference<>(view);
    }

    public void detachView() {
        if (mRef != null) {
            mRef.clear();
            mRef = null;
        }
    }

    public boolean isViewAttached() {
        return mRef != null && mRef.get() != null;
    }

    public V getView() {
        return mRef.get();
    }
}
