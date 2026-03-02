package com.test.mvvm_jetpack.common.loading

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Window
import com.test.mvvm_jetpack.R

class LoadingDialog(context: Context) : Dialog(context) {

    init {
        // 去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        // 设置对话框布局
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_loading, null))
        // 设置对话框不可取消
        setCancelable(false)
        // 设置对话框背景透明
        window?.apply {
            setGravity(Gravity.CENTER)

            val layoutParams = attributes
            layoutParams.width = dpToPx(200)
            layoutParams.height = dpToPx(200)
            attributes = layoutParams

            // 设置背景透明，显示圆角
            setBackgroundDrawableResource(android.R.color.transparent)
        }
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

    private fun dpToPx(dp: Int): Int {
        return (dp * this.context.resources.displayMetrics.density).toInt()
    }
}