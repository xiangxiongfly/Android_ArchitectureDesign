package com.example.mvp.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements HandlerAction {
    protected Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        initViews(rootView);
        initData();
        return rootView;
    }

    /**
     * 获取布局id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化View
     */
    protected abstract void initViews(View rootView);

    /**
     * 初始化数据
     */
    protected abstract void initData();

}
