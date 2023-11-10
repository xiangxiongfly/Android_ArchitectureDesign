package com.example.mvvm_coroutines_retrofit_livedata.entity.bean

data class User(
    var admin: Boolean = false,
    val id: Int = 0,
    val nickname: String? = null,
    val publicName: String? = null,
    val username: String? = null
)