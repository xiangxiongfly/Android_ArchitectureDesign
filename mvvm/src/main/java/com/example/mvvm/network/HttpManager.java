package com.example.mvvm.network;

import com.example.mvvm.bean.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class HttpManager {
    private static OkHttpClient client;

    public static OkHttpClient getOkHttpClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();
        }
        return client;
    }

    public static <T> void post(String url, RequestParams requestParams, HttpCallback callback) {
        FormBody.Builder builder = new FormBody.Builder();
        if (requestParams.size() > 0) {
            for (Map.Entry<String, String> entry : requestParams.getParams().entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        getOkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String json = response.body().string();
                    BaseResponse<T> userResponse = new Gson().fromJson(json, new TypeToken<BaseResponse<T>>() {
                    }.getType());
                    if (userResponse.isSuccessful()) {
                        callback.onSuccess(userResponse.getData());
                    } else {
                        callback.onError(-2, userResponse.getErrorMsg());
                    }
                } else {
                    callback.onError(response.code(), "网络连接失败：" + response.code());
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onError(-1, "网络连接失败：" + e.getMessage());
            }
        });
    }
}
