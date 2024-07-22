package com.example.mvvm_coroutines_retrofit_flow_hilt.ui

import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseActivity
import com.example.mvvm_coroutines_retrofit_flow_hilt.databinding.ActivityLoginBinding
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.ResultState
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.UserBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.utils.showToast
import com.example.mvvm_coroutines_retrofit_flow_hilt.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * View层
 */
@AndroidEntryPoint
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
                        is ResultState.Success<UserBean> -> {
                            mViewBinding.tvDesc.text = it.toString()
                            hideLoadingDialog()
                        }
                        is ResultState.Error -> {
                            mViewBinding.tvDesc.text = it.message
                            hideLoadingDialog()
                        }
                        else -> {}
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