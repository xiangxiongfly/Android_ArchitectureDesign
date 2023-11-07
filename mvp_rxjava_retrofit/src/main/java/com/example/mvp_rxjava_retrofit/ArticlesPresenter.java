package com.example.mvp_rxjava_retrofit;

import com.example.mvp_rxjava_retrofit.base.BaseObserver;
import com.example.mvp_rxjava_retrofit.base.BasePresenter;
import com.example.mvp_rxjava_retrofit.bean.ArticleBean;
import com.example.mvp_rxjava_retrofit.http.ApiException;

import java.util.ArrayList;

public class ArticlesPresenter extends BasePresenter<MainActivity> implements ArticlesContract.IArticlesPresenter {

    @Override
    public void getArticles() {
        addSubscribe(create(ApiService.class).getArticles(), new BaseObserver<ArrayList<ArticleBean>>() {

            @Override
            protected void onSuccess(ArrayList<ArticleBean> list) {
                if (isViewAttached()) {
                    getView().articlesSuccess(list);
                }
            }

            @Override
            protected void onError(ApiException e) {
                if (isViewAttached()) {
                    getView().articlesError(e.getDisplayMessage());
                }
            }
        });
    }
}