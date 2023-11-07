package com.example.mvp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mvp.base.BaseActivity;
import com.example.mvp.simple.activity.ArticlesMvpActivity;
import com.example.mvp.simple.fragment.FragmentWithActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_simple_mvp:
                startActivity(new Intent(mContext, ArticlesMvpActivity.class));
                break;
            case R.id.btn_mvp_fragment:
                startActivity(new Intent(mContext, FragmentWithActivity.class));
                break;
        }
    }
}