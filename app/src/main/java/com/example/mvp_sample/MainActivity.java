package com.example.mvp_sample;

import android.content.Intent;
import android.view.View;

import com.example.mvp_sample.base.BaseActivity;
import com.example.mvp_sample.mvc.ArticlesActivity;
import com.example.mvp_sample.mvp_activity.ArticlesMvpActivity;
import com.example.mvp_sample.mvp_fragment.FragmentWithActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    public void mvcClick(View view) {
        startActivity(new Intent(mContext, ArticlesActivity.class));
    }

    public void mvpActivityClick(View view) {
        startActivity(new Intent(mContext, ArticlesMvpActivity.class));
    }

    public void mvpFragmentClick(View view) {
        startActivity(new Intent(mContext, FragmentWithActivity.class));
    }
}
