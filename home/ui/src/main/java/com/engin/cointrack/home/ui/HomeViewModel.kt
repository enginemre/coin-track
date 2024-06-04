package com.engin.cointrack.home.ui

import android.util.Log
import com.engin.cointrack.core.common.base.BaseViewModel
import com.engin.cointrack.core.common.base.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeViewState, HomeViewEvent>() {

    override fun createInitialState(): HomeViewState = HomeViewState()

    override fun onEvent(event: HomeViewEvent) {
        Log.d("HomeViewModel", "onEvent: $event")
    }
}

data class HomeViewState(
    val loading: Boolean = false,
) : IViewState
