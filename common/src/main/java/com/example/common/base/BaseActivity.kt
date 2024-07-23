package com.example.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

    fun launchMain(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch(Dispatchers.Main) {
            block()
        }
    }

    fun launchIO(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch(Dispatchers.IO) {
            block()
        }
    }

    fun launchDefault(block: suspend CoroutineScope.() -> Unit) {
        lifecycleScope.launch(Dispatchers.Default) {
            block()
        }
    }
}