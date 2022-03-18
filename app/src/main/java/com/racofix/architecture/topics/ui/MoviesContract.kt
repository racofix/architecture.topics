package com.racofix.architecture.topics.ui
import com.racofix.architecture.topics.platform.BaseViewModel

interface MoviesContract {

    sealed class UiState {
        object Nothing : UiState()
        object Loading : UiState()
        data class Success(val value: String) : UiState()
        data class Failure(val exception: Throwable) : UiState()
    }

    sealed class UiEvent {
        object GetMovies : UiEvent()
    }

    abstract class ViewModel : BaseViewModel<UiState, UiEvent>(UiState.Nothing)
}