package com.example.mvp_sample.mvp_activity;

/**
 * 契约类，用于关联MVP三者关系
 */
public class ArticlesContract {
    /**
     * M层接口
     */
    interface IArticlesModel {
        void getArticles(ArticesCallback callback);
    }

    /**
     * V层接口
     */
    interface IArticlesView {
        void articlesSuccess(String success);

        void articlesError(String error);
    }

    /**
     * P层接口
     */
    interface IArticlesPresenter {
        void getArticles();
    }

    /**
     * 网络请求回调
     */
    interface ArticesCallback {
        void onSuccess(String success);

        void onError(String error);
    }
}
