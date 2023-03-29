package com.example.androidrepository.utils

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.androidrepository.domain.repository.RepositoryDomain
import com.example.androidrepository.model.ItemRepo
import com.example.androidrepository.presenter.viewmodels.MainViewModel

class MyPagingSource(private val repositoryDomain: RepositoryDomain) : PagingSource<Int, ItemRepo>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemRepo> {
        try {
            // Загрузка данных из репозитория
            val currentPage = params.key ?: 1
            val response = repositoryDomain.getRepository(currentPage, params.loadSize)

            // Разбиение данных на страницы
            val responseData = response.body()
            val prevKey = if (currentPage == 1) null else currentPage - 1
            val nextKey = if (responseData?.items!!.isEmpty()) null else currentPage + 1

            // Возврат результата загрузки
            return LoadResult.Page(responseData.items, prevKey, nextKey)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, ItemRepo>): Int? {
        return null
    }

}
