package com.example.androidrepository.model

import com.google.gson.annotations.SerializedName

data class ErrorRepo(
    @SerializedName("resource") val resource: String? = null,
    @SerializedName("field") val field: String? = null,
    @SerializedName("code") val code: String? = null,
)