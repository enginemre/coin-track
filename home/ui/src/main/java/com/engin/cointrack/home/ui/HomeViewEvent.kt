package com.engin.cointrack.home.ui

import com.engin.cointrack.core.common.base.IViewEvent

sealed interface HomeViewEvent : IViewEvent {
    data object NavigateBack : HomeViewEvent
}
