package com.racofix.architecture.topics.ui.movie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.racofix.architecture.domain.Result
import com.racofix.architecture.topics.R
import com.racofix.architecture.topics.platform.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MovieFragment : BaseFragment() {

    private val viewModel by viewModels<MovieViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeTo()
    }

    private fun subscribeTo() {
        viewModel.getMoveDetails1 {
            when (this) {
                is Result.Idle -> hideProgress()
                is Result.Loading -> showProgress()
                is Result.Success -> data?.let { value -> textViewDetails.text = value.id }
                is Result.Error -> exception.message?.let { msg -> showMessage(msg) }
            }
        }
        /*
          优点：
              自定义协程，
              生命周期和 ViewModel 绑定
         */
        viewModel.getMoveDetails2 {
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