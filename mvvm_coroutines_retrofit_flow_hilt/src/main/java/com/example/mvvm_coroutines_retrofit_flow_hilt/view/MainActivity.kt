package com.example.mvvm_coroutines_retrofit_flow_hilt.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.mvvm_coroutines_retrofit_flow.databinding.ActivityMainBinding
import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseActivity

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
}