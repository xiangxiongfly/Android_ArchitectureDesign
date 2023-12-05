package com.example.mvvm_coroutines_retrofit_flow_hilt.viewmodel

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseViewModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.ArticleModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.ResultState
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.ArticleBean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleModel: ArticleModel
) : BaseViewModel() {
    private val _articleFlow =
        MutableStateFlow<ResultState<ArrayList<ArticleBean>>>(ResultState.None)
    val articleFlow get() = _articleFlow.asStateFlow()

    fun getArticleList() {
        launchIO {
            articleModel.getArticleList()
                .onStart {
                    _articleFlow.value = ResultState.Loading
                }
                .collect {
                    _articleFlow.value = it
                }
        }
    }
}