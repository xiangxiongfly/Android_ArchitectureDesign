package com.example.mvp.simple.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.R;
import com.example.mvp.adapter.ArticleAdapter;
import com.example.mvp.base.mvp.BaseMvpActivity;
import com.example.mvp.bean.ArticleBean;
import com.example.mvp.bean.Response;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ArticlesMvpActivity extends BaseMvpActivity<ArticlesPresenter> implements ArticlesContract.IArticlesView {
    private ArticleAdapter mAdapter;
    private final ArrayList<ArticleBean> mList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_articles_mvp;
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

    private ProgressDialog progressDialog;

    @Override
    public void showLoading() {
        runOnUiThread(() -> {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(mContext);
                progressDialog.setMessage("加载中");
            }
            progressDialog.show();
        });
    }

    @Override
    public void hideLoading() {
        runOnUiThread(() -> {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.hide();
            }
        });
    }

    @Override
    public void articlesSuccess(Response<ArrayList<ArticleBean>> success) {
        post(new Runnable() {
            @Override
            public void run() {
                hideLoading();
                mList.clear();
                mList.addAll(success.getData());
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void articlesError(String error) {
        post(new Runnable() {
            @Override
            public void run() {
                hideLoading();
                Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}