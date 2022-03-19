package com.racofix.architecture.topics.ui.movie

import com.racofix.architecture.domain.Result
import com.racofix.architecture.domain.entity.Movie
import com.racofix.architecture.domain.usecase.GetMoveDetailsUseCase
import com.racofix.architecture.topics.platform.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

typealias UiState = Result<Movie>.() -> Unit

@HiltViewModel
class MovieViewModel(private val getMoveDetailsUseCase: GetMoveDetailsUseCase) : BaseViewModel() {

    //1. flow -> StateFlow
    fun getMoveDetails1(): StateFlow<Result<Movie>> =
        getMoveDetailsUseCase(GetMoveDetailsUseCase.Params(1))
            .stateIn(this, SharingStarted.WhileSubscribed(), Result.Idle)

    //2. flow -> T.() -> Unit
    fun getMoveDetails2(uiState: UiState) = launch {
        getMoveDetailsUseCase(GetMoveDetailsUseCase.Params(1)).collect {
            uiState.invoke(it)
        }
    }

    //3. flow.onEach
    fun getMoveDetails3(uiState: UiState) = launch {
        getMoveDetailsUseCase(GetMoveDetailsUseCase.Params(1))
            .onEach(uiState)
            .collect()
    }

    //4. flow.launchIn(scope)
    fun getMoveDetails4(uiState: UiState) =
        getMoveDetailsUseCase(GetMoveDetailsUseCase.Params(1))
            .onEach(uiState)
            .launchIn(this)
}