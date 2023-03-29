package com.example.androidrepository.interfaces

import com.example.androidrepository.model.ItemRepo
import com.example.androidrepository.model.RepoModel

interface RepoListener {
    fun onClick(itemRepo: ItemRepo)
}