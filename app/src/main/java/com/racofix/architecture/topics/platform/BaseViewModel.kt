package com.racofix.architecture.topics.platform

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<US, UE>(initialState: US) : ViewModel() {

    var currentState: US = initialState
        @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
        internal set(value) {
            field = value
            value?.let {
                _uiState.value = it
            }
        }

    private val _uiState = MutableStateFlow(initialState)
     val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<UE>()
    private val uiEvent = _uiEvent.asSharedFlow()

    init {
        subscribeToUiEvents()
        this.onViewModelReady()
    }

    private fun subscribeToUiEvents() {
        uiEvent.onEach(::onEachUiEvent).launchIn(viewModelScope)
    }

    open fun onViewModelReady() {}

    //subscribeToUiEvents
    open fun onEachUiEvent(event: UE) {}

    fun setUiState(block: US.() -> US) =
        viewModelScope.launch { _uiState.value = uiState.value.block() }

    fun dispatchUiEvent(event: UE) =
        viewModelScope.launch { _uiEvent.emit(event) }
}