package com.example.mvp_rxjava_retrofit.base;

import android.app.Application;

public class BaseApp extends Application {
    private static BaseApp instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static BaseApp getInstance() {
        return instance;
    }
}
