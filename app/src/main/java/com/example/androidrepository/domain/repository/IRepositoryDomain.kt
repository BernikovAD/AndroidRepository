package com.example.androidrepository.domain.repository

import com.example.androidrepository.domain.IRetrofitAPI
import com.example.androidrepository.model.RepoModel
import retrofit2.Response

abstract class IRepositoryDomain(api: IRetrofitAPI) {
    abstract suspend fun getRepository(page: Int, perPage: Int): Response<RepoModel>
}