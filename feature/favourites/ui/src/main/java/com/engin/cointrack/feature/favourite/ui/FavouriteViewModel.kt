package com.engin.cointrack.feature.favourite.ui

import androidx.lifecycle.viewModelScope
import com.engin.cointrack.core.common.base.BaseViewModel
import com.engin.cointrack.core.common.base.IViewState
import com.engin.cointrack.core.domain.GetFavouriteCoinsUseCase
import com.engin.cointrack.core.model.Coin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val getFavouriteCoinsUseCase: GetFavouriteCoinsUseCase,
) : BaseViewModel<FavouriteViewState, FavouriteViewEvent>() {

    private val refreshTriggerFlow = MutableStateFlow(true)

    val favouriteCoins: StateFlow<PersistentList<Coin>> = refreshTriggerFlow
        .filter {
            if (it.not()) { setState { copy(loading = false) } }
            it
        }
        .flatMapLatest { getFavouriteCoinsUseCase() }
        .onStart { setState { copy(loading = true) } }
        .onEach {
            setState { copy(loading = false, pullToRefreshLoading = false) }
            refreshTriggerFlow.update { false }
        }
        .catch { handleError(it) }
        .map { it.toPersistentList() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = persistentListOf(),
        )

    private fun handleError(throwable: Throwable) {
        setState { copy(loading = false, pullToRefreshLoading = false, errorMessage = throwable.message) }
        refreshTriggerFlow.update { false }
    }

    override fun createInitialState(): FavouriteViewState = FavouriteViewState()

    override fun onEvent(event: FavouriteViewEvent) {
        when (event) {
            FavouriteViewEvent.PullToRefresh -> handlePullToRefresh()
            is FavouriteViewEvent.OnItemClick -> onItemClicked(coin = event.item)
            else -> Unit
        }
    }

    private fun onItemClicked(coin: Coin) {
        sendEvent(FavouriteViewEvent.NavigateCoinDetail(item = coin))
    }

    private fun handlePullToRefresh() {
        setState { copy(pullToRefreshLoading = true) }
        refreshTriggerFlow.tryEmit(true)
    }

    fun shouldRefresh(shouldRefresh: Boolean) {
        if (shouldRefresh) {
            refreshTriggerFlow.tryEmit(true)
        }
    }
}

data class FavouriteViewState(
    val loading: Boolean = false,
    val pullToRefreshLoading: Boolean = false,
    val errorMessage: String? = null,
) : IViewState

internal val shimmerList = listOf(
    Coin(
        uniqueId = 1,
        id = "",
        name = "",
        symbol = "",
        priceStr = "",
    ),
    Coin(
        uniqueId = 2,
        id = "",
        name = "",
        symbol = "",
        priceStr = "",
    ),
    Coin(
        uniqueId = 3,
        id = "",
        name = "",
        symbol = "",
        priceStr = "",
    ),
    Coin(
        uniqueId = 4,
        id = "",
        name = "",
        symbol = "",
        priceStr = "",
    ),
    Coin(
        uniqueId = 5,
        id = "",
        name = "",
        symbol = "",
        priceStr = "",
    ),
    Coin(
        uniqueId = 6,
        id = "",
        name = "",
        symbol = "",
        priceStr = "",
    ),
    Coin(
        uniqueId = 7,
        id = "",
        name = "",
        symbol = "",
        priceStr = "",
    ),
    Coin(
        uniqueId = 8,
        id = "",
        name = "",
        symbol = "",
        priceStr = "",
    ),
    Coin(
        uniqueId = 9,
        id = "",
        name = "",
        symbol = "",
        priceStr = "",
    ),
)
