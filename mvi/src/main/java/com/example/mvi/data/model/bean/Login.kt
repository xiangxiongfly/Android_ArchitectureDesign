package com.example.mvi.data.model.bean

data class Login(
    val id: Int,
    val admin: Boolean,
    val nickname: String,
    val username: String,
    val coinCount: Int,
)
