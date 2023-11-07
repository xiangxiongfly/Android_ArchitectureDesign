package com.example.mvp.simple.activity;

import com.example.mvp.bean.ArticleBean;
import com.example.mvp.bean.Response;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class ArticlesModel implements ArticlesContract.IArticlesModel {

    @Override
    public void getArticles(@NotNull ArticlesContract.ArticlesCallback callback) {
        final String url = "https://wanandroid.com/wxarticle/chapters/json";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onError(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull okhttp3.Response response) throws IOException {
                String json = response.body().string();
                Response<ArrayList<ArticleBean>> bean = new Gson().fromJson(json, new TypeToken<Response<ArrayList<ArticleBean>>>() {
                }.getType());
                callback.onSuccess(bean);
            }
        });
    }
}
