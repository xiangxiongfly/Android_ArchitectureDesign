package com.example.mvp.base.mvp;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.mvp.base.BaseFragment;
import com.example.mvp.base.BasePresenter;

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {
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
