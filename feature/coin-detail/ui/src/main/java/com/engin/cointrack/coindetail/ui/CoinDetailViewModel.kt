package com.engin.cointrack.coindetail.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.engin.cointrack.coindetail.domain.usecase.GetCoinDetailUseCase
import com.engin.cointrack.core.common.base.BaseViewModel
import com.engin.cointrack.core.common.base.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
) : BaseViewModel<CoinDetailViewState, CoinDetailViewEvent>() {

    private val args = CoinDetailArgs(savedStateHandle)

    val coinDetailState = getCoinDetailUseCase(args.id)
        .onStart { setState { copy(loading = true) } }
        .onEach { setState { copy(loading = false) } }
        .catch { handleError(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null,
        )

    private fun handleError(throwable: Throwable) {
        Log.e(coinDetailRoute, throwable.stackTraceToString())
        setState { copy(loading = false, errorMessage = throwable.message) }
    }

    override fun createInitialState(): CoinDetailViewState = CoinDetailViewState()

    override fun onEvent(event: CoinDetailViewEvent) {
        Log.d("CoinDetailViewModel", "onEvent: $event")
    }
}

data class CoinDetailViewState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
) : IViewState
