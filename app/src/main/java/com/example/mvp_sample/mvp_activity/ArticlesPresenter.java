package com.example.mvp_sample.mvp_activity;

import com.example.mvp_sample.base.BasePresenter;

public class ArticlesPresenter extends BasePresenter<ArticlesMvpActivity> implements ArticlesContract.IArticlesPresenter {

    private final ArticlesModel mArticlesModel;

    public ArticlesPresenter() {
        mArticlesModel = new ArticlesModel();
    }

    @Override
    public void getArticles() {
        mArticlesModel.getArticles(new ArticlesContract.ArticesCallback() {
            @Override
            public void onSuccess(String success) {
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
