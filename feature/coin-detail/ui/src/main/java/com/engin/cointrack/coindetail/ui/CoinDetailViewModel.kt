package com.engin.cointrack.coindetail.ui

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.engin.cointrack.coindetail.domain.usecase.GetCoinDetailUseCase
import com.engin.cointrack.core.common.Resource
import com.engin.cointrack.core.common.base.BaseViewModel
import com.engin.cointrack.core.common.base.IViewState
import com.engin.cointrack.core.domain.DeleteFavouriteCoinUseCase
import com.engin.cointrack.core.domain.UpsertFavouriteCoinUseCase
import com.engin.cointrack.core.model.Coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCoinDetailUseCase: GetCoinDetailUseCase,
    private val upsertFavouriteCoinUseCase: UpsertFavouriteCoinUseCase,
    private val deleteFavouriteCoinUseCase: DeleteFavouriteCoinUseCase,
) : BaseViewModel<CoinDetailViewState, CoinDetailViewEvent>() {

    private val args = CoinDetailArgs(savedStateHandle)

    private val refreshTriggerFlow = MutableStateFlow(true)

    val coinDetailState = refreshTriggerFlow
        .filter { it }
        .flatMapLatest { getCoinDetailUseCase(args.id) }
        .onStart { setState { copy(loading = true) } }
        .onEach {
            setState {
                copy(loading = false)
            }
            refreshTriggerFlow.update { false }
        }
        .catch { handleError(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null,
        )

    private fun handleError(throwable: Throwable?) {
        Log.e(coinDetailRoute, throwable?.stackTraceToString().orEmpty())
        setState { copy(loading = false, errorMessage = throwable?.message ?: "Error Occurred") }
        refreshTriggerFlow.update { false }
    }

    override fun createInitialState(): CoinDetailViewState = CoinDetailViewState()

    override fun onEvent(event: CoinDetailViewEvent) {
        Log.d(coinDetailRoute, "onEvent: $event")
        when (event) {
            is CoinDetailViewEvent.OnFavouriteClick -> onFavouriteClick(event.item)
            is CoinDetailViewEvent.OnRemoveFavouriteClick -> onRemoveFavouriteClick(event.item)
            else -> Unit
        }
    }

    private fun onRemoveFavouriteClick(item: Coin) {
        deleteFavouriteCoinUseCase(item).onEach { resource ->
            setState { copy(loading = resource is Resource.Loading) }
            when (resource) {
                Resource.Loading -> Unit
                is Resource.Error -> handleError(resource.exception)
                is Resource.Success -> {
                    setState {
                        copy(
                            showSnackBar = true,
                            snackBarMessage = "Successfully removed",
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun onFavouriteClick(item: Coin) {
        upsertFavouriteCoinUseCase(item).onEach { resource ->
            setState { copy(loading = resource is Resource.Loading) }
            when (resource) {
                Resource.Loading -> Unit
                is Resource.Error -> handleError(resource.exception)
                is Resource.Success -> {
                    setState {
                        copy(
                            showSnackBar = true,
                            snackBarMessage = "Successfully added",
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun refresh() {
        refreshTriggerFlow.update { true }
    }
}

data class CoinDetailViewState(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val showSnackBar: Boolean = false,
    val snackBarMessage: String = "",
) : IViewState
