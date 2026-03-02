package com.test.mvvm_clean.domain.usecase

import com.test.mvvm_clean.domain.repository.ListRepo
import javax.inject.Inject

class ListUseCase @Inject constructor(private val repo: ListRepo) {
    fun getArticles(page: Int) = repo.getArticles(page)
}