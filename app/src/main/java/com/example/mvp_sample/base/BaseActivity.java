package com.example.mvp_sample.base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            initViews();
        }
        mContext = this;
        initPresenter();
        initDatas();
    }

    /**
     * 获取布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化空间
     */
    protected abstract void initViews();

    /**
     * 初始化Presenter
     */
    protected void initPresenter() {

    }

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

    protected void addFragmentToActivity(@IdRes int id, @NonNull Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(id, fragment);
        transaction.commit();
    }
}
