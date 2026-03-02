package com.test.mvvm_jetpack.login.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.test.mvvm_jetpack.common.loading.LoadingDialog
import com.test.mvvm_jetpack.common.model.state.UiState
import com.test.mvvm_jetpack.common.utils.showToast
import com.test.mvvm_jetpack.databinding.ActivityLoginBinding
import com.test.mvvm_jetpack.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels()
    private val loadingDialog by lazy { LoadingDialog(this) }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObserves()
    }

    private fun setupObserves() {
        viewModel.loginState.observe(this) {
            when (it) {
                is UiState.Idle -> {
                    hideLoading()
                }

                is UiState.Loading -> {
                    showLoading()
                }

                is UiState.Success -> {
                    hideLoading()
                    showToast(it.data.toString())
                    binding.tvResult.text = it.data.toString()
                }

                is UiState.Error -> {
                    hideLoading()
                    showToast(it.errMsg)
                    binding.tvResult.text = it.errMsg
                }

                else -> {}
            }
        }
    }

    fun clickLogin(view: View) {
        val username = binding.etName.text.toString()
        val password = binding.etPassword.text.toString()
        viewModel.login(username, password)
    }

    private fun showLoading() {
        loadingDialog.show()
    }

    private fun hideLoading() {
        loadingDialog.dismiss()
    }
}