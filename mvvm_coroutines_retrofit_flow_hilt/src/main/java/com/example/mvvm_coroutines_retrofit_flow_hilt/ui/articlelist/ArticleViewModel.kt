package com.example.mvvm_coroutines_retrofit_flow_hilt.ui.articlelist

import android.util.Log
import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseViewModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.ArticleBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.respository.local.LocalArticleRepo
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.respository.remote.ArticleRepo
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleModel: ArticleRepo,
    private val localArticleRepo: LocalArticleRepo
) : BaseViewModel() {

    private val _articleFlow1 =
        MutableStateFlow<UiState<ArrayList<ArticleBean>>>(UiState.None)
    val articleFlow1 get() = _articleFlow1.asStateFlow()

    private val _articleFlow2 = MutableSharedFlow<UiState<ArrayList<ArticleBean>>>()
    val articleFlow2 get() = _articleFlow2.asSharedFlow()

    fun getArticleList() {
//        launchIO {
//            _articleFlow.value = ResultState.Loading
//
//            localArticleRepo.getCacheArticle()?.let {
//                _articleFlow.value = ResultState.Success(it, isCache = true)
//            }
//
//            _articleFlow.value = apiCall { articleModel.getArticleList() }.also {
//                if (it is ResultState.Success) {
//                    localArticleRepo.saveCacheArticle(it.data)
//                }
//            }
//        }

        launchIO {
            flowOf(
                flow {
                    localArticleRepo.getCacheArticle()?.let {
                        emit(UiState.Success(it, isCache = true))
                    }
                }, apiCallFlow {
                    articleModel.getArticleList()
                }
            ).onStart {
                _articleFlow1.value = UiState.Loading
            }.flatMapConcat { it }
                .collect { result ->
                    Log.e("TAG", "collect: $result")
                    _articleFlow1.value = result
                    result.also {
                        if (it is UiState.Success && !it.isCache) {
                            localArticleRepo.saveCacheArticle(it.data)
                        }
                    }
                }
        }
    }

    fun getArticleListFlow() {
        launchIO {
            flowOf(
                flow {
                    localArticleRepo.getCacheArticle()?.let {
                        emit(UiState.Success(it, isCache = true))
                    }
                }, apiCallFlow {
                    articleModel.getArticleList()
                }
            ).onStart {
                _articleFlow2.emit(UiState.Loading)
            }.flatMapConcat { it }
                .collect { result ->
                    Log.e("TAG", "collect: $result")
                    _articleFlow2.emit(result)
                    result.also {
                        if (it is UiState.Success && !it.isCache) {
                            localArticleRepo.saveCacheArticle(it.data)
                        }
                    }
                }
        }
    }
}