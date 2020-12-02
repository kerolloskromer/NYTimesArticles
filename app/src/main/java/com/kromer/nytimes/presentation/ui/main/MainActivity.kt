package com.kromer.nytimes.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import com.kromer.nytimes.databinding.ActivityMainBinding
import com.kromer.nytimes.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getVBInflater(): (LayoutInflater) -> ActivityMainBinding =
        ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}