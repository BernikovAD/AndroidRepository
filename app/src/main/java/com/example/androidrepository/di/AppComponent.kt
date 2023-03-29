package com.example.androidrepository.di.module

import android.content.Context
import com.example.androidrepository.presenter.MainActivity
import com.example.androidrepository.presenter.mainfragment.MainFragment
import com.example.androidrepository.utils.BaseViewBindingFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [NetworkModule::class, ViewModelModule::class]
)
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(fragment: MainFragment)
    
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

