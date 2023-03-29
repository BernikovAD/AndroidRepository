package com.example.androidrepository.model

import com.google.gson.annotations.SerializedName

data class ItemRepo(
    @SerializedName("name") val name: String,
    @SerializedName("owner") val owner: OwnerRepo,
    @SerializedName("html_url") val html_url: String,
    @SerializedName("stargazers_count") val stargazersCount: String,
    @SerializedName("description") val description: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("created_at") val createdAt: String
)
