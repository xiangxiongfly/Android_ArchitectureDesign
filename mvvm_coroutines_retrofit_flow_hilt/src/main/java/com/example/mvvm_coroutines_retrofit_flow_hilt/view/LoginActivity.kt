package com.example.mvvm_coroutines_retrofit_flow_hilt.view

import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvvm_coroutines_retrofit_flow.databinding.ActivityLoginBinding
import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseActivity
import com.example.mvvm_coroutines_retrofit_flow_hilt.entity.ResultState
import com.example.mvvm_coroutines_retrofit_flow_hilt.entity.bean.User
import com.example.mvvm_coroutines_retrofit_flow_hilt.utils.showToast
import com.example.mvvm_coroutines_retrofit_flow_hilt.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

/**
 * View层
 */
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val viewModel: LoginViewModel by viewModels()

    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(this).apply {
            setMessage("加载中")
        }
    }

    override fun createViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun initViews() {
        mViewBinding.btnLogin.setOnClickListener {
            val username = mViewBinding.etUsername.text.toString()
            val password = mViewBinding.etPassword.text.toString()
            if (username.isEmpty()) {
                showToast("请输入用户名")
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                showToast("请输入密码")
                return@setOnClickListener
            }

            viewModel.login(username, password)
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.userFlow.collect {
                    when (it) {
                        is ResultState.Loading -> showLoadingDialog()
                        is ResultState.Success<User> -> {
                            mViewBinding.tvDesc.text = it.toString()
                        }
                        is ResultState.Error -> mViewBinding.tvDesc.text = it.message
                        is ResultState.Complete -> hideLoadingDialog()
                        else -> {
                        }
                    }
                }
            }
        }
    }

    private fun showLoadingDialog() {
        progressDialog.show()
    }

    private fun hideLoadingDialog() {
        progressDialog.hide()
    }

}