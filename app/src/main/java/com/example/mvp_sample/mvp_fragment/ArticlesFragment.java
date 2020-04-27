package com.example.mvp_sample.mvp_fragment;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mvp_sample.R;
import com.example.mvp_sample.base.BaseMvpFragment;

public class ArticlesFragment extends BaseMvpFragment<ArticlesPresenter> implements ArticlesContract.IArticlesView {

    private Button btn_get_articles;
    private TextView tv_articles;
    private ProgressDialog progressDialog;

    public static Fragment newInstance() {
        return new ArticlesFragment();
    }

    @Override
    protected ArticlesPresenter createPresenter() {
        return new ArticlesPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.articles_layout;
    }

    @Override
    protected void initViews(View rootView) {
        btn_get_articles = rootView.findViewById(R.id.btn_get_articles);
        tv_articles = rootView.findViewById(R.id.tv_articles);
        btn_get_articles.setOnClickListener(v -> {
            showLoading();
            getPresenter().getArticles();
        });
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void articlesSuccess(String success) {
        hideLoading();
        mActivity.runOnUiThread(() -> tv_articles.setText(success));
    }

    @Override
    public void articleError(String error) {
        hideLoading();
        mActivity.runOnUiThread(() -> tv_articles.setText(error));
    }

    @Override
    public void showLoading() {
        mActivity.runOnUiThread(() -> {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(mContext);
                progressDialog.setMessage("加载中");
            }
            progressDialog.show();
        });
    }

    @Override
    public void hideLoading() {
        mActivity.runOnUiThread(() -> {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.hide();
            }
        });
    }
}
