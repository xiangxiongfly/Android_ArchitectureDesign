package com.example.mvp.simple.fragment;

import com.example.mvp.base.BasePresenter;
import com.example.mvp.bean.ArticleBean;
import com.example.mvp.bean.Response;
import com.example.mvp.simple.activity.ArticlesContract;
import com.example.mvp.simple.activity.ArticlesModel;

import java.util.ArrayList;

public class ArticlesPresenter extends BasePresenter<ArticlesFragment> implements ArticlesContract.IArticlesPresenter {
    private ArticlesModel mArticlesModel;

    public ArticlesPresenter() {
        mArticlesModel = new ArticlesModel();
    }

    @Override
    public void getArticles() {
        mArticlesModel.getArticles(new ArticlesContract.ArticlesCallback() {
            @Override
            public void onSuccess(Response<ArrayList<ArticleBean>> success) {
                if (isViewAttached()) {
                    getView().articlesSuccess(success);
                }
            }

            @Override
            public void onError(String error) {
                if (isViewAttached()) {
                    getView().articlesError(error);
                }
            }
        });
    }
}
