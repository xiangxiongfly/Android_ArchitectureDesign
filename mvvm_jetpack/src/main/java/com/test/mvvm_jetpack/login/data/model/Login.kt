package com.test.mvvm_jetpack.login.data.model

data class Login(
    val id: Int,
    val admin: Boolean,
    val nickname: String,
    val username: String,
    val coinCount: Int,
)
