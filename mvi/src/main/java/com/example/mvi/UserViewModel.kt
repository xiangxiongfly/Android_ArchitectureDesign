package com.example.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * 负责管理状态和处理意图。
 */
class UserViewModel : ViewModel() {
    private val _state = MutableStateFlow<UserState>(UserState.NONE)
    val state: StateFlow<UserState> get() = _state

    fun processIntent(intent: UserIntent) {
        when (intent) {
            UserIntent.FetchUsers -> fetchUsers()
        }
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _state.value = UserState.Loading
            try {
                delay(1000)
                val users = listOf(
                    User(1, "hello", "hello@100.com"),
                    User(2, "world", "world@100.com")
                )
                _state.value = UserState.Success(users)
            } catch (e: Exception) {
                _state.value = UserState.Error(e)
            }
        }
    }
}