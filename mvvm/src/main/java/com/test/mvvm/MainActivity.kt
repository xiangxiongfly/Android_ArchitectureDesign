package com.test.mvvm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.test.mvvm.list.ui.ListActivity
import com.test.mvvm.login.ui.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toLogin(view: View) {
        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun toList(view: View) {
        startActivity(Intent(this, ListActivity::class.java))
    }
}