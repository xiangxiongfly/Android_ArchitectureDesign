package com.example.mvp_sample.mvp_fragment;

import com.example.mvp_sample.base.interfaces.IView;

public class ArticlesContract {
    interface IArticlesModel {
        void getArticles(ArticlesCallback callback);
    }

    interface IArticlesView extends IView {
        void articlesSuccess(String success);

        void articleError(String error);
    }

    interface IArticlesPresenter {
        void getArticles();
    }

    interface ArticlesCallback {
        void onSuccess(String success);

        void onError(String error);
    }

}
