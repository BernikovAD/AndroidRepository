package com.example.androidrepository.presenter.mainfragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.androidrepository.MyApplication
import com.example.androidrepository.databinding.FragmentMainBinding
import com.example.androidrepository.interfaces.RepoListener
import com.example.androidrepository.model.ItemRepo
import com.example.androidrepository.presenter.viewmodels.MainState
import com.example.androidrepository.presenter.viewmodels.MainViewModel
import com.example.androidrepository.presenter.viewmodels.ViewModelFactory
import com.example.androidrepository.utils.BaseViewBindingFragment
import javax.inject.Inject

class MainFragment:
BaseViewBindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private lateinit var viewModel: MainViewModel
    private val adapter = RepoAdapter()
    @Inject
    lateinit var factory: ViewModelFactory
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireContext().applicationContext as MyApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        binding.recyclerViewRepo.adapter = adapter
        adapter.setListener(object : RepoListener {
            override fun onClick(itemRepo: ItemRepo) {
                Toast.makeText(requireContext(),"${itemRepo.name}",Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.observeState(viewLifecycleOwner){
            when(it){
                MainState.Loading -> {
                    binding.recyclerViewRepo.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is MainState.Success -> {
                    binding.recyclerViewRepo.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    if(it.repoModel.errors!=null&&it.repoModel.message!=null){
                        Toast.makeText(requireContext(),"${it.repoModel.message}",Toast.LENGTH_SHORT).show()
                    }else{
                        if(it.repoModel.items?.isNotEmpty() == true){
                            adapter.submitList(it.repoModel.items)
                        }else {
                            Toast.makeText(requireContext(),"Список пуст",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
    companion object {
        fun newInstance() = MainFragment()
    }
}