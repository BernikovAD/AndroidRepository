package com.example.androidrepository.model

import com.google.gson.annotations.SerializedName

data class OwnerRepo(
    @SerializedName("login") val login: String
)
