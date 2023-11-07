package com.example.mvp.base.mvp;

import com.example.mvp.base.BaseActivity;
import com.example.mvp.base.BasePresenter;

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    private P mPresenter;

    @Override
    protected void initViews() {
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initMvpViews();
    }

    protected abstract void initMvpViews();

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();

    protected P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
    }
}
