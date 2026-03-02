package com.test.mvvm_jetpack.list.data.model

import com.google.gson.annotations.SerializedName

data class ArticleData(
    val curPage: Int = 0,
    val pageCount: Int = 0,
    val total: Long = 0,
    val size: Int = 0,
    @SerializedName("datas")
    val list: List<Article>
)

data class Article(
    val canEdit: Boolean,
    val chapterName: String,
    val niceDate: String,
    val title: String,
    val userId: Int,
)