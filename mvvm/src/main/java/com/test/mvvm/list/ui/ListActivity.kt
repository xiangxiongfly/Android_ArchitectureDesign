package com.test.mvvm.list.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.common.utils.showToast
import com.test.mvvm.common.loading.LoadingDialog
import com.test.mvvm.databinding.ActivityListBinding
import com.test.mvvm.list.data.ListRepo
import com.test.mvvm.list.data.state.ListState
import com.test.mvvm.list.viewmodel.ListViewModel
import com.test.mvvm.list.viewmodel.ListViewModelFactory

class ListActivity : AppCompatActivity() {
    private val viewModel: ListViewModel by viewModels {
        ListViewModelFactory(ListRepo())
    }
    private val loadingDialog by lazy { LoadingDialog(this) }
    private lateinit var binding: ActivityListBinding
    private lateinit var adapter: ListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRv()
        loadList()
        setupObserves()
    }

    private fun initRv() {
        adapter = ListAdapter()
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        binding.refreshLayout.setOnRefreshListener {
            viewModel.refreshList()
        }
        binding.refreshLayout.setOnLoadMoreListener {
            viewModel.loadMoreList()
        }
    }

    private fun loadList() {
        viewModel.loadList()
    }

    private fun refreshList() {
        viewModel.refreshList()
    }

    private fun loadMoreList() {
        viewModel.loadMoreList()
    }

    private fun setupObserves() {
        viewModel.listState.observe(this) {
            when (it) {
                is ListState.Idle -> {
                    hideLoading()
                }

                is ListState.Loading -> {
                    showLoading()
                }

                is ListState.Success -> {
                    hideLoading()
                    adapter.updateItems(it.items)
                }

                is ListState.RefreshSuccess -> {
                    binding.refreshLayout.finishRefresh()
                    adapter.updateItems(it.items)
                }

                is ListState.LoadMoreSuccess -> {
                    binding.refreshLayout.finishLoadMore()
                    adapter.appendItems(it.items)
                }

                is ListState.Error -> {
                    hideLoading()
                    complete()
                    showToast(it.errMsg)
                }

                else -> {}
            }
        }
    }

    private fun complete() {
        binding.refreshLayout.finishRefresh()
        binding.refreshLayout.finishLoadMore()
    }

    private fun showLoading() {
        loadingDialog.show()
    }

    private fun hideLoading() {
        loadingDialog.dismiss()
    }
}