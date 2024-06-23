package com.engin.cointrack.coindetail.ui

import com.engin.cointrack.core.common.base.IViewEvent

sealed interface CoinDetailViewEvent : IViewEvent {
    data object NavigateBack : CoinDetailViewEvent
}
