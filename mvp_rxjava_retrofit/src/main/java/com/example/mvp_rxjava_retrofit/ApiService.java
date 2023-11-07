package com.example.mvp_rxjava_retrofit;

import com.example.mvp_rxjava_retrofit.bean.ArticleBean;
import com.example.mvp_rxjava_retrofit.bean.BaseResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    @GET("wxarticle/chapters/json")
    Observable<BaseResponse<ArrayList<ArticleBean>>> getArticles();
}

