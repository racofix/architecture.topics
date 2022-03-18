package com.racofix.architecture.topics.ui.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.racofix.architecture.domain.Result
import com.racofix.architecture.topics.R
import com.racofix.architecture.topics.platform.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collect

class MovieFragment : BaseFragment() {

    lateinit var viewModel: MovieViewModel

    override fun layoutId(): Int = R.layout.activity_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeTo()
    }

    private fun subscribeTo() {
        lifecycleScope.launchWhenCreated {
            viewModel.getMoveDetails1().collect {
                when (it) {
                    is Result.Idle -> hideProgress()
                    is Result.Loading -> showProgress()
                    is Result.Success -> it.data?.let { value -> textViewDetails.text = value.id }
                    is Result.Error -> it.exception.message?.let { msg -> showMessage(msg) }
                }
            }
        }

        /*
          2 - 4 全部采用此方式,
          优点：全部采用 自定义协程，根据ViewModel 生命周期绑定
          flow 冷流 StateFlow 热流
         */
        viewModel.getMoveDetails4 {
            when (this) {
                is Result.Idle -> hideProgress()
                is Result.Loading -> showProgress()
                is Result.Success -> data?.let { value -> textViewDetails.text = value.id }
                is Result.Error -> exception.message?.let { msg -> showMessage(msg) }
            }
        }
    }

    private fun hideProgress() {
        progress.visibility = View.GONE
        textViewDetails.visibility = View.VISIBLE
    }

    private fun showProgress() {
        progress.visibility = View.VISIBLE
        textViewDetails.visibility = View.GONE
    }

    private fun showMessage(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
    }
}