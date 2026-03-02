package com.test.mvvm.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mvvm.common.model.state.ListState
import com.test.mvvm.common.model.state.ResultState
import com.test.mvvm.list.data.ListRepo
import com.test.mvvm.list.data.model.Article
import kotlinx.coroutines.launch

class ListViewModel(private val repo: ListRepo) : ViewModel() {
    private val _listState = MutableLiveData<ListState<Article>>(ListState.Idle)
    val listState: LiveData<ListState<Article>> get() = _listState
    private var currentPage = 1
    private val allItems = mutableListOf<Article>()

    fun loadList() {
        if (_listState.value is ListState.Loading ||
            _listState.value is ListState.Refreshing ||
            _listState.value is ListState.LoadingMore
        ) {
            return
        }
        _listState.value = ListState.Loading
        viewModelScope.launch {
            val result = repo.getArticles(currentPage)
            when (result) {
                is ResultState.Success -> {
                    val items = result.data.list
                    allItems.addAll(items)
                    _listState.value = ListState.Success(items)
                    currentPage++
                }

                is ResultState.Error -> {
                    _listState.value = ListState.Error(result.errMsg)
                }
            }
        }
    }

    fun refreshList() {
        if (_listState.value is ListState.Loading ||
            _listState.value is ListState.Refreshing ||
            _listState.value is ListState.LoadingMore
        ) {
            return
        }

        _listState.value = ListState.Refreshing
        viewModelScope.launch {
            currentPage = 1
            allItems.clear()
            val result = repo.getArticles(currentPage)
            when (result) {
                is ResultState.Success -> {
                    val items = result.data.list
                    allItems.addAll(items)
                    _listState.value = ListState.RefreshSuccess(allItems)
                    currentPage++
                }

                is ResultState.Error -> {
                    _listState.value = ListState.Error(result.errMsg)
                }
            }
        }
    }

    fun loadMoreList() {
        if (_listState.value is ListState.Loading ||
            _listState.value is ListState.Refreshing ||
            _listState.value is ListState.LoadingMore
        ) {
            return
        }

        _listState.value = ListState.LoadingMore
        viewModelScope.launch {
            val result = repo.getArticles(currentPage)
            when (result) {
                is ResultState.Success -> {
                    val items = result.data.list
                    allItems.addAll(items)
                    _listState.value = ListState.LoadMoreSuccess(items)
                    currentPage++
                }

                is ResultState.Error -> {
                    _listState.value = ListState.Error(result.errMsg)
                }
            }
        }
    }
}