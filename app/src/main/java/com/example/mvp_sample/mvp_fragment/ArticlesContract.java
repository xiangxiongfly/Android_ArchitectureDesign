package com.example.mvp_sample.mvp_fragment;

import com.example.mvp_sample.base.interfaces.IView;

/**
 * 契约类，关联MVP三者关系
 */
public class ArticlesContract {
    /**
     * M层接口
     */
    interface IArticlesModel {
        void getArticles(ArticlesCallback callback);
    }

    /**
     * V层接口
     */
    interface IArticlesView extends IView {
        void articlesSuccess(String success);

        void articleError(String error);
    }

    /**
     * P层接口
     */
    interface IArticlesPresenter {
        void getArticles();
    }

    /**
     * 数据接口回调
     */
    interface ArticlesCallback {
        void onSuccess(String success);

        void onError(String error);
    }

}
