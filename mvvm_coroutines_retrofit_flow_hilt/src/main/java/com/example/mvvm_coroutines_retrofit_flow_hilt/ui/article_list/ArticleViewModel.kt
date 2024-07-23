package com.example.mvvm_coroutines_retrofit_flow_hilt.ui.article_list

import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseViewModel
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.ArticleBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.respository.local.LocalArticleRepo
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.respository.remote.ArticleRepo
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.state.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val articleModel: ArticleRepo,
    private val localArticleRepo: LocalArticleRepo
) : BaseViewModel() {

    private val _articleFlow =
        MutableStateFlow<ResultState<ArrayList<ArticleBean>>>(ResultState.None)
    val articleFlow get() = _articleFlow.asStateFlow()

    private val _articleCacheFlow = MutableStateFlow<ArrayList<ArticleBean>?>(null)
    val articleCacheFlow get() = _articleCacheFlow.asStateFlow()

    fun getArticleListCache() {
        launchIO {
            localArticleRepo.getCacheArticle()?.let {
                _articleCacheFlow.value = it
            }
        }
    }

    fun getArticleList() {
        launchIO {
            apiCall { articleModel.getArticleList() }
                .onStart {
                    _articleFlow.value = ResultState.Loading
                }
                .collect {
                    _articleFlow.value = it
                    if (it is ResultState.Success) {
                        localArticleRepo.saveCacheArticle(it.data)
                    }
                }
        }
    }
}