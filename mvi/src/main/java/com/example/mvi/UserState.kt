package com.example.mvi

/**
 * 视图状态持有当前用户界面的状态
 */
sealed class UserState {
    object NONE : UserState()
    object Loading : UserState()
    data class Success(val users: List<User>) : UserState()
    data class Error(val error: Throwable) : UserState()
}