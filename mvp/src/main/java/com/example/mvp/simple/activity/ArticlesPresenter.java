package com.example.mvp.simple.activity;

import com.example.mvp.base.BasePresenter;
import com.example.mvp.bean.ArticleBean;
import com.example.mvp.bean.Response;

import java.util.ArrayList;

public class ArticlesPresenter extends BasePresenter<ArticlesMvpActivity> implements ArticlesContract.IArticlesPresenter {
    private final ArticlesModel mArticlesModel;

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
