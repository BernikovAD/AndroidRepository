package com.example.androidrepository.presenter.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidrepository.domain.repository.RepositoryDomain
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val repositoryDomain: RepositoryDomain
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) ->
                MainViewModel(repositoryDomain) as T
            else -> throw  IllegalArgumentException("Unknown ViewModel class")
        }
    }

}