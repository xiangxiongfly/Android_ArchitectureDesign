package com.test.mvvm.common.loading

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.test.mvvm.R

class LoadingDialog(context: Context) : Dialog(context) {

    init {
        // 去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        // 设置对话框布局
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_loading, null))
        // 设置对话框不可取消
        setCancelable(false)
        // 设置对话框背景透明
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    override fun show() {
        if (!isShowing) {
            super.show()
        }
    }

    override fun dismiss() {
        if (isShowing) {
            super.dismiss()
        }
    }
}