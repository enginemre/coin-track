package com.engin.cointrack.coindetail.ui

import com.engin.cointrack.core.common.base.IViewEvent
import com.engin.cointrack.core.model.Coin

sealed interface CoinDetailViewEvent : IViewEvent {
    data class OnFavouriteClick(val item: Coin) : CoinDetailViewEvent
    data class OnRemoveFavouriteClick(val item: Coin) : CoinDetailViewEvent
}
