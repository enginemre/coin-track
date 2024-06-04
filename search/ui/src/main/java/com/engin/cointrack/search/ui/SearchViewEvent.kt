package com.engin.cointrack.search.ui

import com.engin.cointrack.core.common.base.IViewEvent

sealed interface SearchViewEvent : IViewEvent {
    data object NavigateBack : SearchViewEvent
}
