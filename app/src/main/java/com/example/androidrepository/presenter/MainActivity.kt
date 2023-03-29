package com.example.androidrepository.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidrepository.R
import com.example.androidrepository.presenter.mainfragment.MainFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.root_container,
            MainFragment.newInstance()
        ).commit()
    }
}