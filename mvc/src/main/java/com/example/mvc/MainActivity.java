package com.example.mvc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvc.bean.ArticleBean;
import com.example.mvc.bean.BaseBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnGetData;
    private RecyclerView rvArticles;

    private ArrayList<ArticleBean> mList = new ArrayList<>();
    private ArticleAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        btnGetData = findViewById(R.id.btn_get_data);
        rvArticles = findViewById(R.id.rv_articles);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        mAdapter = new ArticleAdapter(this, mList);
        rvArticles.setAdapter(mAdapter);
    }

    /**
     * 获取数据
     */
    private void getData() {
        final String url = "https://wanandroid.com/wxarticle/chapters/json";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = response.body().string();
                BaseBean<ArrayList<ArticleBean>> result = new Gson().fromJson(json, new TypeToken<BaseBean<ArrayList<ArticleBean>>>() {
                }.getType());
                setData(result.getData());
            }
        });
    }

    /**
     * 设置数据
     */
    private void setData(ArrayList<ArticleBean> data) {
        mList.clear();
        mList.addAll(data);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

}