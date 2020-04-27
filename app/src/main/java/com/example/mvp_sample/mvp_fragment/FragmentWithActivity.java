package com.example.mvp_sample.mvp_fragment;

import com.example.mvp_sample.R;
import com.example.mvp_sample.base.BaseActivity;

public class FragmentWithActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment2;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        addFragmentToActivity(R.id.fl_container, ArticlesFragment.newInstance());
    }
}
