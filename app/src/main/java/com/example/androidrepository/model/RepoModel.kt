package com.example.androidrepository.model

import com.google.gson.annotations.SerializedName

data class RepoModel(
    @SerializedName("total_count") val totalCount: Long=0,
    @SerializedName("incomplete_results") val incompleteResults: Boolean=false,
    @SerializedName("items") val items: List<ItemRepo>?=null,
    @SerializedName("message") val message: String?=null,
    @SerializedName("errors") val errors: ErrorRepo?=null,
    @SerializedName("documentation_url") val documentationUrl: String?=null,
)
