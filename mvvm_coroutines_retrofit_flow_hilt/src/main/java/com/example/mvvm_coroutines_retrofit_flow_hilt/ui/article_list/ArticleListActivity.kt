package com.example.mvvm_coroutines_retrofit_flow_hilt.ui.article_list

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.example.common.base.BaseActivity
import com.example.common.utils.showToast
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.bean.ArticleBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.data.state.ResultState
import com.example.mvvm_coroutines_retrofit_flow_hilt.databinding.ActivityArticleListBinding
import com.example.mvvm_coroutines_retrofit_flow_hilt.ui.article_list.adapter.ArticleAdapter
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
        observe()
    }

    private fun getArticleList() {
        articleListViewModel.getArticleListCache()
        articleListViewModel.getArticleList()
    }

    private fun observe() {
        launchMain {
            articleListViewModel.articleCacheFlow.collect {
                it?.let {
                    Log.e("TAG", "缓存")
                    updateUI(it)
                }
            }
        }
        launchMain {
            articleListViewModel.articleFlow.collect {
                when (it) {
                    is ResultState.Loading -> showLoading()
                    is ResultState.Error -> {
                        showToast(it.message)
                        hideLoading()
                    }
                    is ResultState.Success -> {
                        hideLoading()
                        Log.e("TAG", "网络")
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
