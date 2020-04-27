package com.example.mvp_sample.mvp_fragment;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ArticlesModel implements ArticlesContract.IArticlesModel {
    @Override
    public void getArticles(ArticlesContract.ArticlesCallback callback) {
        String url = "https://wanandroid.com/wxarticle/chapters/json";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                if (callback != null) {
                    callback.onError(e.getMessage());
                }
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                String result = response.body().string();
                if (callback != null) {
                    callback.onSuccess(result);
                }
            }
        });
    }
}
