package com.engin.cointrack.search.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.engin.cointrack.core.common.base.BaseViewModel
import com.engin.cointrack.core.common.base.IViewState
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.feature.search.domain.GetCoinsWithQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCoinsWithQueryUseCase: GetCoinsWithQueryUseCase,
) : BaseViewModel<SearchViewState, SearchViewEvent>() {

    override fun createInitialState(): SearchViewState = SearchViewState()

    var coins: Flow<PagingData<Coin>> = uiState
        .map { it.searchText }
        .distinctUntilChanged()
        .debounce(200)
        .flatMapLatest { text ->
            getCoinsWithQueryUseCase(text)
        }.cachedIn(viewModelScope)

    override fun onEvent(event: SearchViewEvent) {
        when (event) {
            SearchViewEvent.NavigateBack -> TODO()
            is SearchViewEvent.OnSearchTextChange -> setState { copy(searchText = event.text) }
            is SearchViewEvent.OnItemClick -> sendEvent(SearchViewEvent.OnItemClick(event.item))
            SearchViewEvent.OnClearText -> setState { copy(searchText = "") }
        }
    }
}

data class SearchViewState(
    val loading: Boolean = false,
    val searchText: String = "",
) : IViewState
