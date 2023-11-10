package com.example.mvvm_coroutines_retrofit_livedata.http.exceptions

class ServerException(val code: Int, val msg: String) : RuntimeException()