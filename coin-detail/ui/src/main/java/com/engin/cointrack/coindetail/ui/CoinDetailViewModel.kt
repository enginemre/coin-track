package com.engin.cointrack.coindetail.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.engin.cointrack.core.common.base.BaseViewModel
import com.engin.cointrack.core.common.base.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<CoinDetailViewState, CoinDetailViewEvent>() {

    private val args = CoinDetailArgs(savedStateHandle)
    init {
        setState { copy(id = args.id) }
    }
    override fun createInitialState(): CoinDetailViewState = CoinDetailViewState()

    override fun onEvent(event: CoinDetailViewEvent) {
        Log.d("CoinDetailViewModel", "onEvent: $event")
    }
}

data class CoinDetailViewState(
    val loading: Boolean = false,
    val id: String = "",
) : IViewState
