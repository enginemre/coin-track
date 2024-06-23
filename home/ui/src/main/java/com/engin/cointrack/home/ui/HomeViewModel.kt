package com.engin.cointrack.home.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.engin.cointrack.core.common.base.BaseViewModel
import com.engin.cointrack.core.common.base.IViewState
import com.engin.cointrack.domain.model.Coin
import com.engin.cointrack.domain.usecase.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
) : BaseViewModel<HomeViewState, HomeViewEvent>() {

    val coinListState: Flow<PagingData<Coin>> = getCoinsUseCase().cachedIn(viewModelScope)

    override fun createInitialState(): HomeViewState = HomeViewState()

    override fun onEvent(event: HomeViewEvent) {
        Log.d("HomeViewModel", "onEvent: $event")
        when (event) {
            is HomeViewEvent.OnItemClick -> {
                sendEvent(HomeViewEvent.NavigateToDetail(event.id))
            }
            else -> Unit
        }
    }
}

data class HomeViewState(
    val errorMessage: String? = null,
) : IViewState

val shimmerList = listOf(
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
