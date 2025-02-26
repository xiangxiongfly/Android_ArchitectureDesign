package com.example.mvvm_clean.ui

import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.common.base.BaseActivity
import com.example.common.utils.showToast
import com.example.mvvm_clean.databinding.ActivityLoginBinding
import com.example.mvvm_clean.data.model.bean.User
import com.example.mvvm_clean.data.model.state.UiState
import kotlinx.coroutines.launch

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
                viewModel.loginFlow.collect {
                    when (it) {
                        is UiState.Loading -> showLoading()

                        is UiState.Success<User> -> {
                            mViewBinding.tvDesc.text = it.toString()
                            hideLoading()
                        }

                        is UiState.Error -> {
                            mViewBinding.tvDesc.text = it.message
                            hideLoading()
                        }

                        else -> {
                            hideLoading()
                        }
                    }
                }
            }
        }
    }

    private fun showLoading() {
        progressDialog.show()
    }

    private fun hideLoading() {
        progressDialog.hide()
    }
}