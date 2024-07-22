package com.example.mvvm_coroutines_retrofit_livedata.view

import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.viewModels
import com.example.mvvm_coroutines_retrofit_livedata.base.BaseActivity
import com.example.mvvm_coroutines_retrofit_livedata.databinding.ActivityLoginBinding
import com.example.mvvm_coroutines_retrofit_livedata.entity.ResultState
import com.example.mvvm_coroutines_retrofit_livedata.utils.showToast
import com.example.mvvm_coroutines_retrofit_livedata.viewmodel.LoginViewModel

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
            if (username.isNullOrEmpty()) {
                showToast("请输入用户名")
                return@setOnClickListener
            }
            if (password.isNullOrEmpty()) {
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
        viewModel.loginLiveData.observe(this) {
            when (it) {
                is ResultState.Loading -> {
                    showLoadingDialog()
                }
                is ResultState.Error -> {
                    hideLoadingDialog()
                    mViewBinding.tvDesc.text = it.message
                }
                is ResultState.Success<*> -> {
                    hideLoadingDialog()
                    mViewBinding.tvDesc.text = it.data.toString()
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