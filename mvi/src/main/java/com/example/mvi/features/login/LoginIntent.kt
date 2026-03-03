package com.example.mvi.features.login

sealed class LoginIntent {
    data class Login(val username: String, val password: String) : LoginIntent()
    object ResetState : LoginIntent()
}