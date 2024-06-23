package com.engin.cointrack.home.ui

import com.engin.cointrack.core.common.base.IViewEvent

sealed interface HomeViewEvent : IViewEvent {
    data class OnItemClick(val id: String) : HomeViewEvent
    data class NavigateToDetail(val id: String) : HomeViewEvent
}
