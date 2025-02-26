package com.example.mvvm_coroutines_retrofit_flow_hilt.ui.articlelist

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.common.base.BaseActivity
import com.example.common.utils.showToast
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.ArticleBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.state.UiState
import com.example.mvvm_coroutines_retrofit_flow_hilt.databinding.ActivityArticleListBinding
import com.example.mvvm_coroutines_retrofit_flow_hilt.ui.articlelist.adapter.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleListActivity : BaseActivity<ActivityArticleListBinding>() {
    private val mList = ArrayList<ArticleBean>()
    private val articleListViewModel by viewModels<ArticleViewModel>()
    private val progressDialog: ProgressDialog by lazy {
        ProgressDialog(this).apply {
            setMessage("加载中")
        }
    }

    override fun createViewBinding(): ActivityArticleListBinding {
        return ActivityArticleListBinding.inflate(layoutInflater)
    }

    override fun initViews() {
    }

    override fun initData(savedInstanceState: Bundle?) {
        mViewBinding.rvArticleList.adapter = ArticleAdapter(this, mList)
        mViewBinding.btnGetArticleList.setOnClickListener {
            getArticleList()
        }
        mViewBinding.btnGetArticleListFlow.setOnClickListener {
            getArticleListFlow()
        }
        observe()
    }

    private fun getArticleList() {
        articleListViewModel.getArticleList()
    }

    private fun getArticleListFlow() {
        articleListViewModel.getArticleListFlow()
    }

    private fun observe() {
        launchMain {
            articleListViewModel.articleFlow1.collect {
                when (it) {
                    is UiState.Loading -> showLoading()
                    is UiState.Error -> {
                        showToast(it.message)
                        hideLoading()
                    }
                    is UiState.Success<ArrayList<ArticleBean>> -> {
                        hideLoading()
                        if (it.isCache) {
                            Log.e("TAG", "来自缓存")
                        } else {
                            Log.e("TAG", "来自网络")
                        }
                        updateUI(it.data)
                    }
                    else -> {}
                }
            }
        }
        launchMain {
            articleListViewModel.articleFlow2.collect {
                when (it) {
                    is UiState.Loading -> showLoading()
                    is UiState.Error -> {
                        showToast(it.message)
                        hideLoading()
                    }
                    is UiState.Success<ArrayList<ArticleBean>> -> {
                        hideLoading()
                        if (it.isCache) {
                            Log.e("TAG", "来自缓存")
                        } else {
                            Log.e("TAG", "来自网络")
                        }
                        updateUI(it.data)
                    }
                    else -> {}
                }
            }
        }
    }

    private fun updateUI(list: ArrayList<ArticleBean>?) {
        mList.clear()
        if (list != null) {
            mList.addAll(list)
        }
        mViewBinding.rvArticleList.adapter!!.notifyDataSetChanged()
    }

    private fun showLoading() {
        progressDialog.show()
    }

    private fun hideLoading() {
        progressDialog.hide()
    }
}
