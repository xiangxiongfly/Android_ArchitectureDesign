package com.example.mvp_sample.base;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.mvp_sample.base.interfaces.IPresenter;
import com.example.mvp_sample.base.interfaces.IView;

public abstract class BaseMvpFragment<P extends IPresenter> extends BaseFragment implements IView {

    private P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract P createPresenter();

    protected P getPresenter() {
        return mPresenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
