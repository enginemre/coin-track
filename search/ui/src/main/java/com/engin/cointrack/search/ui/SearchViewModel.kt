package com.engin.cointrack.search.ui

import com.engin.cointrack.core.common.base.BaseViewModel
import com.engin.cointrack.core.common.base.IViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor() : BaseViewModel<SearchViewState, SearchViewEvent>() {

    override fun createInitialState(): SearchViewState = SearchViewState()

    override fun onEvent(event: SearchViewEvent) {
        when (event) {
            SearchViewEvent.NavigateBack -> TODO()
        }
    }
}

data class SearchViewState(
    val loading: Boolean = false,
) : IViewState
