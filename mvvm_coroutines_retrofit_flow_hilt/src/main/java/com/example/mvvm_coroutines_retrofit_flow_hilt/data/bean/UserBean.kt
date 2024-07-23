package com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean

data class UserBean(
    var admin: Boolean = false,
    val id: Int = 0,
    val nickname: String? = null,
    val publicName: String? = null,
    val username: String? = null
)