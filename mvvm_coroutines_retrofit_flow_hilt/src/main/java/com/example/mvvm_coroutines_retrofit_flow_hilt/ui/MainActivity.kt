package com.example.mvvm_coroutines_retrofit_flow_hilt.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.common.base.BaseActivity
import com.example.mvvm_coroutines_retrofit_flow_hilt.databinding.ActivityMainBinding
import com.example.mvvm_coroutines_retrofit_flow_hilt.ui.article_list.ArticleListActivity
import com.example.mvvm_coroutines_retrofit_flow_hilt.ui.login.LoginActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun createViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initViews() {
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    fun toLoginActivity(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun toArticleListActivity(view: View) {
        startActivity(Intent(this, ArticleListActivity::class.java))
    }
}