package com.test.mvvm.list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.mvvm.list.data.ListRepo

class ListViewModelFactory(private val repo: ListRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}