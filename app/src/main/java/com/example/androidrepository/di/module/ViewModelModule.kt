package com.example.androidrepository.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.androidrepository.domain.repository.RepositoryDomain
import com.example.androidrepository.presenter.viewmodels.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {
    @Provides
    fun viewModelFactory(
        repositoryDomain: RepositoryDomain
    ): ViewModelProvider.Factory {
        return ViewModelFactory(repositoryDomain)
    }
}