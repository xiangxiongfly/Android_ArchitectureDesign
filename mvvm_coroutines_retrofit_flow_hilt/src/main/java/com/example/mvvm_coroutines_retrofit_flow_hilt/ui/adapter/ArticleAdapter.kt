package com.example.mvvm_coroutines_retrofit_flow_hilt.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_coroutines_retrofit_flow.databinding.ItemLayoutBinding
import com.example.mvvm_coroutines_retrofit_flow_hilt.model.bean.ArticleBean

class ArticleAdapter(private val mContext: Context, private val mList: List<ArticleBean>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(mContext)

    class ViewHolder(binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvName = binding.tvName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemLayoutBinding.inflate(mLayoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article: ArticleBean = mList[position]
        holder.tvName.text = "${article.name} - ${article.order}"
    }

    override fun getItemCount(): Int = mList.size
}