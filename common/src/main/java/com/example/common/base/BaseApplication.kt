package com.example.common.base

import android.app.Application
import com.scwang.smart.refresh.footer.ClassicsFooter
import com.scwang.smart.refresh.header.ClassicsHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

open class BaseApplication : Application() {
    companion object {
        private lateinit var instance: BaseApplication

        fun getInstance(): BaseApplication {
            return instance
        }
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            ClassicsHeader(context)
        }
        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            ClassicsFooter(context)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}