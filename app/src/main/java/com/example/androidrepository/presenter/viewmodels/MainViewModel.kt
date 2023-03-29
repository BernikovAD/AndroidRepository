package com.example.androidrepository.presenter.viewmodels


import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.androidrepository.domain.repository.RepositoryDomain
import com.example.androidrepository.model.ItemRepo
import com.example.androidrepository.model.RepoModel
import com.example.androidrepository.utils.BaseViewModel
import com.example.androidrepository.utils.MyPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repositoryDomain: RepositoryDomain
) : BaseViewModel<MainState>(MainState.Loading)  {

    private var currentResult: Flow<PagingData<ItemRepo>>? = null

    suspend fun getRepository(): Flow<PagingData<ItemRepo>> {
            val newResult: Flow<PagingData<ItemRepo>> = Pager(PagingConfig(pageSize = 20)) {
                MyPagingSource(repositoryDomain)
            }.flow.cachedIn(viewModelScope)
            currentResult = newResult
            return newResult
    }

}