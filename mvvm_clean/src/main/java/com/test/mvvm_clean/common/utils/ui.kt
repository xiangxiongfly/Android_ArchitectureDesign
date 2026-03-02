package com.test.mvvm_clean.common.utils

import android.widget.Toast
import com.test.mvvm_clean.common.app.BaseApp

fun showToast(msg: String) {
    Toast.makeText(BaseApp.getInstance(), msg, Toast.LENGTH_SHORT).show()
}