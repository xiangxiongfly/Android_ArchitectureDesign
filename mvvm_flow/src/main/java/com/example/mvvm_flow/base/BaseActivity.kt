package com.example.mvvm_flow.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * 封装Activity
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {
    private lateinit var _mViewBinding: VB
    protected val mViewBinding get() = _mViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _mViewBinding = createViewBinding()
        setContentView(_mViewBinding.root)
        initViews()
        initData(savedInstanceState)
    }

    abstract fun createViewBinding(): VB

    abstract fun initViews()

    abstract fun initData(savedInstanceState: Bundle?)
}