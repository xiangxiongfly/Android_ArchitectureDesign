package com.example.mvi.common.utils

import android.widget.Toast
import com.example.mvi.common.app.BaseApp

fun showToast(msg: String) {
    Toast.makeText(BaseApp.getInstance(), msg, Toast.LENGTH_SHORT).show()
}