package com.engin.cointrack.search.ui

import com.engin.cointrack.core.common.base.IViewEvent
import com.engin.cointrack.core.model.Coin

sealed interface SearchViewEvent : IViewEvent {
    data object NavigateBack : SearchViewEvent
    data object OnClearText : SearchViewEvent

    data class OnSearchTextChange(val text: String) : SearchViewEvent

    data class OnItemClick(val item: Coin) : SearchViewEvent
}
