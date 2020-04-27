package com.example.mvp_sample.mvp_fragment;

import com.example.mvp_sample.base.BasePresenter;

public class ArticlesPresenter extends BasePresenter<ArticlesFragment> implements ArticlesContract.IArticlesPresenter {

    private final ArticlesModel mArticlesModel;

    public ArticlesPresenter() {
        mArticlesModel = new ArticlesModel();
    }

    @Override
    public void getArticles() {
        mArticlesModel.getArticles(new ArticlesContract.ArticlesCallback() {
            @Override
            public void onSuccess(String success) {
                if (isViewAttached()) {
                    getView().articlesSuccess(success);
                }
            }

            @Override
            public void onError(String error) {
                if (isViewAttached()) {
                    getView().articleError(error);
                }
            }
        });
    }
}
