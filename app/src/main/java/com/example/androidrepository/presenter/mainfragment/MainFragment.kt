package com.example.androidrepository.presenter.mainfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.androidrepository.MyApplication
import com.example.androidrepository.R
import com.example.androidrepository.databinding.FragmentMainBinding
import com.example.androidrepository.interfaces.RepoListener
import com.example.androidrepository.model.ItemRepo
import com.example.androidrepository.presenter.detailsfragment.DetailsFragment
import com.example.androidrepository.presenter.viewmodels.MainState
import com.example.androidrepository.presenter.viewmodels.MainViewModel
import com.example.androidrepository.presenter.viewmodels.ViewModelFactory
import com.example.androidrepository.utils.BaseViewBindingFragment
import com.example.androidrepository.utils.MyPagingSource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment:
BaseViewBindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private  val viewModel: MainViewModel by activityViewModels<MainViewModel> { factory }
    private val adapter = RepoAdapter()
    @Inject
    lateinit var factory: ViewModelFactory
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireContext().applicationContext as MyApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        //viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        binding.recyclerViewRepo.adapter = adapter
        adapter.setListener(object : RepoListener {
            override fun onClick(itemRepo: ItemRepo) {
                viewModel.item = itemRepo
                val detailsFragment = DetailsFragment.newInstance()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.root_container, detailsFragment)
                    .commit()
            }
        })
        lifecycleScope.launch {
            viewModel.getRepository().collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
    companion object {
        fun newInstance() = MainFragment()
    }
}