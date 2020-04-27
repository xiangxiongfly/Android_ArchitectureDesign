package com.example.mvp_sample.mvp_activity;

public class ArticlesContract {
    interface IArticlesModel {
        void getArticles(ArticesCallback callback);
    }

    interface IArticlesView {
        void articlesSuccess(String success);

        void articlesError(String error);
    }

    interface IArticlesPresenter {
        void getArticles();
    }

    //网络请求回调
    interface ArticesCallback {
        void onSuccess(String success);

        void onError(String error);
    }
}
