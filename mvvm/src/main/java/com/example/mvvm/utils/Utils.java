package com.example.mvvm.utils;

import android.widget.Toast;

import com.example.mvvm.BaseApp;

public class Utils {
    public static void showToast(String text) {
        Toast.makeText(BaseApp.getApp(), text, Toast.LENGTH_SHORT).show();
    }
}
