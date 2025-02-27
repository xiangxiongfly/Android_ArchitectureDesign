package com.example.common.utils

import android.widget.Toast
import com.example.common.base.BaseApplication

fun showToast(text: String) {
    Toast.makeText(BaseApplication.getInstance(), text, Toast.LENGTH_SHORT).show();
}

fun showLongToast(text: String) {
    Toast.makeText(BaseApplication.getInstance(), text, Toast.LENGTH_LONG).show();
}