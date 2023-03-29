package com.example.androidrepository.presenter.viewmodels


import androidx.lifecycle.viewModelScope
import com.example.androidrepository.domain.repository.RepositoryDomain
import com.example.androidrepository.model.RepoModel
import com.example.androidrepository.utils.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repositoryDomain: RepositoryDomain
) : BaseViewModel<MainState>(MainState.Loading)  {

    var parPage = 30
    var page = 1

    init {
        getRepository()
    }

    fun getRepository(){
        state.value = MainState.Loading
        viewModelScope.launch(Dispatchers.IO) {
             val response = repositoryDomain.getRepository(page.toString(),parPage.toString())
                state.postValue(MainState.Success(response.body()!!) )
            }
    }
}