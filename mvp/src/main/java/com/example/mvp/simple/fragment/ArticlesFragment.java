package com.example.mvp.simple.fragment;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mvp.R;
import com.example.mvp.adapter.ArticleAdapter;
import com.example.mvp.base.mvp.BaseMvpFragment;
import com.example.mvp.bean.ArticleBean;
import com.example.mvp.bean.Response;
import com.example.mvp.simple.activity.ArticlesContract;

import java.util.ArrayList;

public class ArticlesFragment extends BaseMvpFragment<ArticlesPresenter> implements ArticlesContract.IArticlesView {
    private ArticleAdapter mAdapter;
    private final ArrayList<ArticleBean> mList = new ArrayList<>();
    private ProgressDialog progressDialog;

    public static ArticlesFragment newInstance() {
        return new ArticlesFragment();
    }

    @Override
    protected ArticlesPresenter createPresenter() {
        return new ArticlesPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_articles_mvp;
    }

    @Override
    protected void initViews(View rootView) {
        Button btnGetData = rootView.findViewById(R.id.btn_get_data);
        RecyclerView rvArticles = rootView.findViewById(R.id.rv_articles);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoading();
                getPresenter().getArticles();
            }
        });

        mAdapter = new ArticleAdapter(mContext, mList);
        rvArticles.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {

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
