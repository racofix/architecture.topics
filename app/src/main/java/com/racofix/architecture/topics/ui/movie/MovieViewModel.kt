package com.racofix.architecture.topics.ui.movie

import com.racofix.architecture.domain.entity.MovieUiState
import com.racofix.architecture.domain.usecase.GetMoveDetailsUseCase
import com.racofix.architecture.topics.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val getMoveDetailsUseCase: GetMoveDetailsUseCase
) : BaseViewModel() {

    //2. flow.collect(T.() -> Unit)
    fun getMoveDetails1(uiState: MovieUiState) = launch {
        getMoveDetailsUseCase(GetMoveDetailsUseCase.Params(1))
            .collect(uiState)
    }

    //4. flow.launchIn(scope)
    fun getMoveDetails2(uiState: MovieUiState) =
        getMoveDetailsUseCase(GetMoveDetailsUseCase.Params(1))
            .onEach(uiState)
            .launchIn(this)
}