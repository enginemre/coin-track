package com.engin.cointrack.feature.favourite.ui

import com.engin.cointrack.core.common.base.IViewEvent
import com.engin.cointrack.core.model.Coin

sealed interface FavouriteViewEvent : IViewEvent {
    data object PullToRefresh : FavouriteViewEvent
    data class NavigateCoinDetail(val item: Coin) : FavouriteViewEvent
    data class OnItemClick(val item: Coin) : FavouriteViewEvent
}
