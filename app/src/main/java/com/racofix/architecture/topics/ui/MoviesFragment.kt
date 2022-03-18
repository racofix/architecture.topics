package com.racofix.architecture.topics.ui

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.racofix.architecture.topics.R
import com.racofix.architecture.topics.platform.BaseFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MoviesFragment : BaseFragment() {

    lateinit var viewModel: MoviesViewModel

    override fun layoutId(): Int = R.layout.activity_main

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.dispatchUiEvent(MoviesContract.UiEvent.GetMovies)

        viewModel.uiState.onEach(::onEachUiState).launchIn(lifecycleScope)
    }

    private fun onEachUiState(uiState: MoviesContract.UiState) {

        tips_tv.text = ""


        when (uiState) {
            is MoviesContract.UiState.Loading -> {}
            is MoviesContract.UiState.Success -> {}
            is MoviesContract.UiState.Failure -> {}
            else -> {}
        }
    }
}