package com.example.mvp_sample.mvc;

import android.widget.Button;
import android.widget.TextView;

import com.example.mvp_sample.R;
import com.example.mvp_sample.base.BaseActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ArticlesActivity extends BaseActivity {

    private Button btn_get_articles;
    private TextView tv_articles;

    @Override
    protected int getLayoutId() {
        return R.layout.articles_layout;
    }

    @Override
    protected void initViews() {
        btn_get_articles = findViewById(R.id.btn_get_articles);
        tv_articles = findViewById(R.id.tv_articles);
        btn_get_articles.setOnClickListener(v -> getArticles());
    }

    @Override
    protected void initDatas() {

    }

    private void getArticles() {
        String url = "https://wanandroid.com/wxarticle/chapters/json";
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (tv_articles != null) {
                    setArticlesContent(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (tv_articles != null) {
                    String result = response.body().string();
                    setArticlesContent(result);
                }
            }
        });
    }

    private void setArticlesContent(final String content) {
        runOnUiThread(() -> tv_articles.setText(content));
    }
}
