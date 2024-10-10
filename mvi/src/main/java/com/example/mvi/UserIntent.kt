package com.example.mvi

/**
 * 用户意图表示用户的操作，例如点击按钮或滑动列表。
 */
sealed class UserIntent {
    object FetchUsers : UserIntent()
}