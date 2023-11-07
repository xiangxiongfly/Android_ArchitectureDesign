package com.example.mvp_rxjava_retrofit;

import androidx.annotation.NonNull;

import com.example.mvp_rxjava_retrofit.bean.ArticleBean;
import com.example.mvp_rxjava_retrofit.bean.BaseResponse;

import java.util.ArrayList;


/**
 * 契约接口，用于关联MVP三者关系
 */
public interface ArticlesContract {

    /**
     * View层接口
     */
    interface IArticlesView {
        void showLoading();

        void hideLoading();

        void articlesSuccess(ArrayList<ArticleBean> list);

        void articlesError(String error);
    }

    /**
     * Presenter层接口
     */
    interface IArticlesPresenter {
        void getArticles();
    }

}
