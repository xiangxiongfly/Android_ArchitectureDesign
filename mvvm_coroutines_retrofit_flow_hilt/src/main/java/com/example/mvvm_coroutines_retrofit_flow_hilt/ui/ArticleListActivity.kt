package com.example.mvvm_coroutines_retrofit_flow_hilt.ui

import android.app.ProgressDialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvvm_coroutines_retrofit_flow_hilt.base.BaseActivity
import com.example.mvvm_coroutines_retrofit_flow_hilt.databinding.ActivityArticleListBinding
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.ResultState
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.ArticleBean
import com.example.mvvm_coroutines_retrofit_flow_hilt.ui.adapter.ArticleAdapter
import com.example.mvvm_coroutines_retrofit_flow_hilt.utils.showToast
import com.example.mvvm_coroutines_retrofit_flow_hilt.viewmodel.ArticleViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArticleListActivity : BaseActivity<ActivityArticleListBinding>() {
    private val mList = arrayListOf<ArticleBean>()
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
        articleListViewModel.getArticleList()
    }

    private fun observe() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                articleListViewModel.articleFlow.collect {
                    when (it) {
                        is ResultState.Loading -> showLoading()
                        is ResultState.Error -> {
                            showToast(it.message)
                            hideLoading()
                        }
                        is ResultState.Success -> {
                            hideLoading()
                            updateUI(it.data)
                        }
                        else -> {}
                    }
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
