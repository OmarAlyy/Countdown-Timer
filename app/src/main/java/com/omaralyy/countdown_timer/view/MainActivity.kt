package com.omaralyy.countdown_timer.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.omaralyy.countdown_timer.R
import com.omaralyy.countdown_timer.databinding.ActivityMainBinding
import com.omaralyy.countdown_timer.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewModel = viewModel

    }
}