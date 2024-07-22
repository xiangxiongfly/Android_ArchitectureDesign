package com.example.common.utils

import android.widget.Toast
import com.example.common.base.BaseApplication

fun showToast(text: String) {
    Toast.makeText(BaseApplication.instance, text, Toast.LENGTH_SHORT).show();
}