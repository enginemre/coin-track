package com.engin.cointrack.core.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update

interface IViewState

interface IViewEvent

abstract class BaseViewModel<State : IViewState, Event : IViewEvent> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    protected val currentState: State get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState

    private val _uiEvent: Channel<Event> = Channel()
    val uiEvent: Flow<Event> = _uiEvent.receiveAsFlow()

    protected fun sendEvent(event: Event) {
        _uiEvent.trySend(event)
    }

    abstract fun onEvent(event: Event)

    protected fun setState(reduce: State.() -> State) {
        _uiState.update { currentState.reduce() }
    }
}
