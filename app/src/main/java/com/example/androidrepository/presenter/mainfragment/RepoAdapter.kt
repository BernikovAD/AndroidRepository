package com.example.androidrepository.presenter.mainfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.androidrepository.databinding.ItemRepoBinding
import com.example.androidrepository.interfaces.RepoListener
import com.example.androidrepository.model.ItemRepo


class RepoAdapter: PagingDataAdapter<ItemRepo, RepoViewHolder>(
    DIFF_CALLBACK
) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemRepo>() {
            override fun areItemsTheSame(oldItem: ItemRepo, newItem: ItemRepo) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: ItemRepo, newItem: ItemRepo) =
                oldItem.name == newItem.name
        }
    }

    private lateinit var listener: RepoListener

    fun setListener(listener: RepoListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        return RepoViewHolder(
            binding = ItemRepoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )

    }


    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!)
    }
}