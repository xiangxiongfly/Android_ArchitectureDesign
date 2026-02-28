package com.test.mvvm.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mvvm.list.data.ListRepo
import com.test.mvvm.list.data.model.Article
import com.test.mvvm.list.data.state.ListState
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
            repo.getArticles(currentPage)
                .onSuccess {
                    val items = it.list
                    allItems.addAll(items)
                    _listState.value = ListState.Success(items)
                    currentPage++
                }
                .onFailure {
                    _listState.value = ListState.Error(it.message ?: "加载失败")
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
            repo.getArticles(currentPage)
                .onSuccess {
                    val items = it.list
                    allItems.addAll(items)
                    _listState.value = ListState.RefreshSuccess(allItems)
                    currentPage++
                }
                .onFailure {
                    _listState.value = ListState.Error(it.message ?: "刷新失败")
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
            repo.getArticles(currentPage)
                .onSuccess {
                    val items = it.list
                    allItems.addAll(items)
                    _listState.value = ListState.LoadMoreSuccess(items)
                    currentPage++
                }
                .onFailure {
                    _listState.value = ListState.Error(it.message ?: "加载更多失败")
                }
        }
    }
}