package com.racofix.architecture.topics.ui.movie

import com.racofix.architecture.domain.Result
import com.racofix.architecture.domain.entity.Movie
import com.racofix.architecture.domain.usecase.GetMoveDetailsUseCase
import com.racofix.architecture.topics.platform.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MovieViewModel(private val getMoveDetailsUseCase: GetMoveDetailsUseCase) : BaseViewModel() {

    //1. flow -> StateFlow
    suspend fun getMoveDetails1(): StateFlow<Result<Movie>> =
        getMoveDetailsUseCase(GetMoveDetailsUseCase.Params(1))
            .stateIn(this, SharingStarted.WhileSubscribed(), Result.Idle)

    //2. flow -> T.() -> Unit
    fun getMoveDetails2(uiState: Result<Movie>.() -> Unit) = launch {
        getMoveDetailsUseCase(GetMoveDetailsUseCase.Params(1)).collect {
            uiState.invoke(it)
        }
    }

    //3. flow.onEach
    fun getMoveDetails3(uiState: Result<Movie>.() -> Unit) = launch {
        getMoveDetailsUseCase(GetMoveDetailsUseCase.Params(1))
            .onEach { uiState(it) }
            .collect()
    }

    //4. flow.launchIn(scope)
    fun getMoveDetails4(uiState: Result<Movie>.() -> Unit) = launch {
        getMoveDetailsUseCase(GetMoveDetailsUseCase.Params(1))
            .onEach(uiState)
            .launchIn(this)
    }
}