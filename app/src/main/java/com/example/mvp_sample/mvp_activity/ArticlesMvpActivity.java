package com.example.mvp_sample.mvp_activity;

import android.app.ProgressDialog;
import android.widget.Button;
import android.widget.TextView;

import com.example.mvp_sample.R;
import com.example.mvp_sample.base.BaseMvpActivity;

public class ArticlesMvpActivity extends BaseMvpActivity<ArticlesPresenter> implements ArticlesContract.IArticlesView {


    private Button btn_get_articles;
    private TextView tv_articles;

    @Override
    protected ArticlesPresenter createPresenter() {
        return new ArticlesPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.articles_layout;
    }

    @Override
    protected void initViews() {
        btn_get_articles = findViewById(R.id.btn_get_articles);
        tv_articles = findViewById(R.id.tv_articles);
        btn_get_articles.setOnClickListener(v -> {
            showLoading();
            getPresenter().getArticles();
        });
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void articlesSuccess(final String success) {
        hideLoading();
        runOnUiThread(() -> tv_articles.setText(success));
    }

    @Override
    public void articlesError(final String error) {
        hideLoading();
        runOnUiThread(() -> tv_articles.setText(error));
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
}
