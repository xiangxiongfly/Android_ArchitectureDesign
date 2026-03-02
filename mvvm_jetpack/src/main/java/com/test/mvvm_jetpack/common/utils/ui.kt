package com.test.mvvm_jetpack.common.utils

import android.widget.Toast
import com.test.mvvm_jetpack.BaseApp

fun showToast(msg: String) {
    Toast.makeText(BaseApp.getInstance(), msg, Toast.LENGTH_SHORT).show()
}