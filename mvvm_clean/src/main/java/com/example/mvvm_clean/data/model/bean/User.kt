package com.example.mvvm_clean.data.model.bean

data class User(
    var admin: Boolean = false,
    val id: Int = 0,
    val nickname: String? = null,
    val publicName: String? = null,
    val username: String? = null
)