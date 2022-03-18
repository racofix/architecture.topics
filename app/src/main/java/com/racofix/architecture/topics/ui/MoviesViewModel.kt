package com.racofix.architecture.topics.ui

import androidx.lifecycle.viewModelScope
import com.racofix.architecture.topics.platform.Result
import com.racofix.architecture.topics.usecase.GetMoveDetails
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MoviesViewModel(val getMoveDetails: GetMoveDetails) : MoviesContract.ViewModel() {

    override fun onEachUiEvent(event: MoviesContract.UiEvent) {
        when (event) {
            is MoviesContract.UiEvent.GetMovies -> getMoveDetailsEvent()
        }
    }

    private fun getMoveDetailsEvent() {
        viewModelScope.launch {
            getMoveDetails(GetMoveDetails.Params(1)).collect {
                when (it) {
                    is Result.Loading -> setUiState { MoviesContract.UiState.Loading }
                    is Result.Success -> setUiState { MoviesContract.UiState.Success(it.data) }
                    is Result.Failure -> setUiState { MoviesContract.UiState.Failure(it.exception) }
                }
            }
        }
    }
}