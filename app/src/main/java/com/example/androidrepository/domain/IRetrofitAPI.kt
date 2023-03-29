package com.example.androidrepository.domain

import com.example.androidrepository.model.RepoModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface IRetrofitAPI {
    @GET("search/repositories?q=android&sort=stars")
    suspend fun getRepository(
        @Query("page") page: String,
        @Query("per_page") perPage: String
    ): Response<RepoModel>
}