package com.example.mvp_rxjava_retrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp_rxjava_retrofit.adapter.ArticleAdapter;
import com.example.mvp_rxjava_retrofit.base.mvp.BaseMvpActivity;
import com.example.mvp_rxjava_retrofit.bean.ArticleBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainActivity extends BaseMvpActivity<ArticlesPresenter> implements ArticlesContract.IArticlesView {
    private ProgressDialog progressDialog;
    private ArticleAdapter mAdapter;
    private final ArrayList<ArticleBean> mList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initMvpViews() {
        Button btnGetData = findViewById(R.id.btn_get_data);
        RecyclerView rvArticles = findViewById(R.id.rv_articles);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                getPresenter().getArticles();
            }
        });

        mAdapter = new ArticleAdapter(this, mList);
        rvArticles.setAdapter(mAdapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @NotNull
    @Override
    protected ArticlesPresenter createPresenter() {
        return new ArticlesPresenter();
    }

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setMessage("加载中");
        }
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }

    @Override
    public void articlesSuccess(ArrayList<ArticleBean> list) {
        hideLoading();
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void articlesError(String error) {
        hideLoading();
        Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
    }
}