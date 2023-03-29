package com.example.androidrepository.presenter.viewmodels

import com.example.androidrepository.model.ErrorRepo
import com.example.androidrepository.model.RepoModel
import com.example.androidrepository.utils.IViewModelState


sealed class MainState : IViewModelState {
    object Loading : MainState()
    data class Success(
        val repoModel: RepoModel
  /*      val list: List<RepoModel>,
        val error: ErrorRepo? = null,
        val message: String? = null,
        val documentationUrl: String? = null*/
    ) : MainState()

/*    data class Error(val error: ErrorRepo, val message: String, val documentationUrl: String) :
        MainState()*/
}