package com.example.mvvm_jetpack.ui

import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.viewModels
import com.example.common.base.BaseActivity
import com.example.common.utils.showToast
import com.example.mvvm_jetpack.databinding.ActivityLoginBinding
import com.example.mvvm_jetpack.ui.state.UiState

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
        viewModel.loginState.observe(this) {
            when (it) {
                is UiState.Loading -> {
                    showLoadingDialog()
                }
                is UiState.Error -> {
                    hideLoadingDialog()
                    mViewBinding.tvDesc.text = it.errMsg
                }
                is UiState.Success -> {
                    hideLoadingDialog()
                    mViewBinding.tvDesc.text = it.data.toString()
                }
                else -> {}
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