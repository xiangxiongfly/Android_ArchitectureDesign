package com.example.mvp.simple.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.mvp.R;
import com.example.mvp.base.BaseActivity;

public class FragmentWithActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_with;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        addFragmentToActivity(R.id.fl_container, ArticlesFragment.newInstance());
    }
}