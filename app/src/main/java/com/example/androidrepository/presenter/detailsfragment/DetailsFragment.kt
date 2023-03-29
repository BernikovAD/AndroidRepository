package com.example.androidrepository.presenter.detailsfragment


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.androidrepository.MyApplication
import com.example.androidrepository.R
import com.example.androidrepository.databinding.FragmentDetailsBinding
import com.example.androidrepository.presenter.mainfragment.MainFragment
import com.example.androidrepository.presenter.viewmodels.MainViewModel
import com.example.androidrepository.presenter.viewmodels.ViewModelFactory
import com.example.androidrepository.utils.BaseViewBindingFragment
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DetailsFragment :
    BaseViewBindingFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel: MainViewModel by activityViewModels { factory }

    @Inject
    lateinit var factory: ViewModelFactory

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireContext().applicationContext as MyApplication).appComponent.inject(this)
        super.onViewCreated(view, savedInstanceState)
        if (viewModel.item != null) {
            val item = viewModel.item!!
            binding.tvToolbarTitle.text = item.name
            binding.texViewNameAuthor.text = item.owner.login
            binding.texViewDescriptionImpl.text = item.description
            binding.texViewRatingCount.text = item.stargazersCount
            binding.texViewDateImpl.text = "${countDay(item.updatedAt,item.createdAt)} days"
            binding.buttonOpenRepo.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.html_url)))
            }
            binding.imageViewBack.setOnClickListener {
                val mainFragment = MainFragment.newInstance()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.root_container, mainFragment)
                    .commit()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun countDay(date1:String, dateCreate:String):Long{
        val formatter = DateTimeFormatter.ISO_DATE_TIME
        val pasr1 = LocalDateTime.parse(dateCreate, formatter)
        val pasr2 = LocalDateTime.parse(date1, formatter)
        return Duration.between(pasr1,pasr2).toDays()
    }

    companion object {
        fun newInstance() = DetailsFragment()
    }
}