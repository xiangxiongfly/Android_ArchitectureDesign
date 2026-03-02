package com.test.mvvm.login.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.common.utils.showToast
import com.test.mvvm.common.loading.LoadingDialog
import com.test.mvvm.common.model.state.UiState
import com.test.mvvm.databinding.ActivityLoginBinding
import com.test.mvvm.login.data.LoginRepo
import com.test.mvvm.login.viewmodel.LoginViewModel
import com.test.mvvm.login.viewmodel.LoginViewModelFactory

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(LoginRepo())
    }
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