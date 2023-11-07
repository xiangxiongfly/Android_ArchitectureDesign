package com.example.mvp.simple.activity;

import androidx.annotation.NonNull;

import com.example.mvp.bean.ArticleBean;
import com.example.mvp.bean.Response;

import java.util.ArrayList;

/**
 * 契约接口，用于关联MVP三者关系
 */
public interface ArticlesContract {

    /**
     * Model层接口
     */
    interface IArticlesModel {
        void getArticles(@NonNull ArticlesCallback callback);
    }

    /**
     * View层接口
     */
    interface IArticlesView {
        void showLoading();

        void hideLoading();

        void articlesSuccess(Response<ArrayList<ArticleBean>> success);

        void articlesError(String error);
    }

    /**
     * Presenter层接口
     */
    interface IArticlesPresenter {
        void getArticles();
    }

    /**
     * 回调接口
     */
    interface ArticlesCallback {
        void onSuccess(Response<ArrayList<ArticleBean>> success);

        void onError(String error);
    }
}
