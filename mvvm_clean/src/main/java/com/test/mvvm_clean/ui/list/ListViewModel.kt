package com.test.mvvm_clean.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mvvm_clean.data.model.bean.Article
import com.test.mvvm_clean.data.model.state.ListState
import com.test.mvvm_clean.data.model.state.ResultState
import com.test.mvvm_clean.domain.usecase.ListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val listUseCase: ListUseCase) : ViewModel() {
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
            listUseCase.getArticles(currentPage).collect {
                when (it) {
                    is ResultState.Success -> {
                        val items = it.data.list
                        allItems.addAll(items)
                        _listState.value = ListState.Success(items)
                        currentPage++
                    }

                    is ResultState.Error -> {
                        _listState.value = ListState.Error(it.errMsg)
                    }
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
            listUseCase.getArticles(currentPage).collect {
                when (it) {
                    is ResultState.Success -> {
                        val items = it.data.list
                        allItems.addAll(items)
                        _listState.value = ListState.RefreshSuccess(allItems)
                        currentPage++
                    }

                    is ResultState.Error -> {
                        _listState.value = ListState.Error(it.errMsg)
                    }
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
            listUseCase.getArticles(currentPage).collect {
                when (it) {
                    is ResultState.Success -> {
                        val items = it.data.list
                        allItems.addAll(items)
                        _listState.value = ListState.LoadMoreSuccess(items)
                        currentPage++
                    }

                    is ResultState.Error -> {
                        _listState.value = ListState.Error(it.errMsg)
                    }
                }
            }
        }
    }
}