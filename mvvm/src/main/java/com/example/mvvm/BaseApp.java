package com.example.mvvm;

import android.app.Application;

public class BaseApp extends Application {
    private static BaseApp app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static BaseApp getApp() {
        return app;
    }

}
