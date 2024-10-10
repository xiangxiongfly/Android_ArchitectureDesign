package com.example.mvi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        observeUsers()
    }

    fun onClick(view: View) {
        userViewModel.processIntent(UserIntent.FetchUsers)
    }

    private fun observeUsers() {
        lifecycleScope.launch {
            userViewModel.state.collect { state ->
                when (state) {
                    is UserState.Loading -> showLoading()
                    is UserState.Success -> showUsers(state.users)
                    is UserState.Error -> showError(state.error)
                    else -> {}
                }
            }
        }
    }

    private fun showLoading() {
        Log.e("TAG", "loading")
    }

    private fun showUsers(users: List<User>) {
        Log.e("TAG", "成功：${users}")
    }

    private fun showError(e: Throwable) {
        Log.e("TAG", "失败：${e.message}")
    }
}