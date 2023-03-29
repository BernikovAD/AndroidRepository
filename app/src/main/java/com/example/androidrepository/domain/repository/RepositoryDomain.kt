package com.example.androidrepository.domain.repository

import com.example.androidrepository.domain.IRetrofitAPI
import com.example.androidrepository.model.RepoModel
import retrofit2.Response
import javax.inject.Inject

class RepositoryDomain @Inject constructor(private val api: IRetrofitAPI) : IRepositoryDomain(api) {
    override suspend fun getRepository(page:String,perPage:String): Response<RepoModel> {
        return api.getRepository(page=page,perPage=perPage)
    }
}