package com.example.mvp_rxjava_retrofit.base.mvp;

import com.example.mvp_rxjava_retrofit.base.BaseActivity;
import com.example.mvp_rxjava_retrofit.base.BasePresenter;

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
