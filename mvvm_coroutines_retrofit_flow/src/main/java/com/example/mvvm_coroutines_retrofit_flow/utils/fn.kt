package com.example.mvvm_coroutines_retrofit_flow.utils

import android.widget.Toast
import com.example.mvvm_coroutines_retrofit_flow.base.BaseApplication

fun showToast(text: String) {
    Toast.makeText(BaseApplication.app, text, Toast.LENGTH_SHORT).show()
}