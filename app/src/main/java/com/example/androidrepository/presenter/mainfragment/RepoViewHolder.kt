package com.example.androidrepository.presenter.mainfragment

import androidx.recyclerview.widget.RecyclerView
import com.example.androidrepository.databinding.ItemRepoBinding
import com.example.androidrepository.interfaces.RepoListener
import com.example.androidrepository.model.ItemRepo

class RepoViewHolder (val binding: ItemRepoBinding, val listener: RepoListener) :
    RecyclerView.ViewHolder(binding.root) {

        fun bind(itemRepo: ItemRepo){
            binding.texViewName.text = itemRepo.name
            binding.textViewStar.text = itemRepo.stargazersCount
            binding.texViewNameAuthor.text = itemRepo.owner.login
            binding.root.setOnClickListener {
                listener.onClick(itemRepo)
            }
        }
}