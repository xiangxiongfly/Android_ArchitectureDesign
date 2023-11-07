package com.example.mvp_rxjava_retrofit.base;

import com.example.mvp_rxjava_retrofit.http.HttpManager;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter<V> {
    private WeakReference<V> mReference;
    protected CompositeDisposable mCompositeDisposable;

    public void attachView(V view) {
        mReference = new WeakReference<>(view);
    }

    public void detachView() {
        if (mReference != null) {
            mReference.clear();
            mReference = null;
        }
        unSubscribe();
    }

    public boolean isViewAttached() {
        return mReference != null && mReference.get() != null;
    }

    public V getView() {
        return mReference.get();
    }

    /**
     * 添加订阅
     */
    protected void addSubscribe(Observable<?> observable, BaseObserver observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        BaseObserver baseObserver = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(observer);
        mCompositeDisposable.add(baseObserver);
    }

    /**
     * 取消订阅
     */
    protected void unSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }

    protected <T> T create(Class<T> clazz) {
        return HttpManager.getInstance().getRetrofit().create(clazz);
    }
}

