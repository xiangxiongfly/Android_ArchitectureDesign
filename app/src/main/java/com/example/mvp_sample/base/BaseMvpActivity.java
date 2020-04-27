package com.example.mvp_sample.base;

import com.example.mvp_sample.base.interfaces.IPresenter;
import com.example.mvp_sample.base.interfaces.IView;

public abstract class BaseMvpActivity<P extends IPresenter> extends BaseActivity implements IView {

    private P mPresenter;

    //初始化Presenter
    @Override
    protected void initPresenter() {
        super.initPresenter();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    /**
     * 创建Presenter
     */
    protected abstract P createPresenter();

    /**
     * 获取Presenter
     */
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
