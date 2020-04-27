package com.example.mvp_sample.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    protected BaseActivity mActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (BaseActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        initViews(rootView);
        initDatas();
        return rootView;
    }

    /**
     * 获取布局Id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化控件
     */
    protected abstract void initViews(View rootView);

    /**
     * 初始化数据
     */
    protected abstract void initDatas();
}
