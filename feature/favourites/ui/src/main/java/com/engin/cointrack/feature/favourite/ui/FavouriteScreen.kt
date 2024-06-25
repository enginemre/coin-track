package com.engin.cointrack.feature.favourite.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.designsystem.component.ErrorContent
import com.engin.cointrack.feature.favourite.ui.component.FavouriteCoinCard
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.Flow

@Composable
fun FavouriteRoute(
    shouldRefresh: Boolean,
    navigateCoinDetail: (String) -> Unit,
    viewModel: FavouriteViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val favouriteCoinList by viewModel.favouriteCoins.collectAsStateWithLifecycle()

    LaunchedEffect(shouldRefresh) {
        viewModel.shouldRefresh(shouldRefresh)
    }

    EventCollector(
        navigateCoinDetail = navigateCoinDetail,
        uiEvent = viewModel.uiEvent,
    )

    FavouriteScreen(
        uiState = uiState,
        onViewEvent = viewModel::onEvent,
        favouriteCoinList = favouriteCoinList,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavouriteScreen(
    uiState: FavouriteViewState,
    favouriteCoinList: PersistentList<Coin>,
    onViewEvent: (FavouriteViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.pullToRefreshLoading,
        onRefresh = { onViewEvent(FavouriteViewEvent.PullToRefresh) },
    )
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .padding(paddingValues)
                .padding(WindowInsets.statusBars.asPaddingValues()),
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                if (uiState.loading) {
                    items(shimmerList) {
                        FavouriteCoinCard(
                            modifier = Modifier.padding(vertical = 4.dp),
                            name = it.name,
                            imageUrl = it.imageUrl,
                            symbol = it.symbol,
                            onClick = remember { { onViewEvent(FavouriteViewEvent.OnItemClick(it)) } },
                            isLoading = true,
                        )
                    }
                } else if (uiState.errorMessage.isNullOrEmpty().not() || favouriteCoinList.isEmpty()) {
                    item {
                        ErrorContent(
                            modifier = Modifier.fillParentMaxSize(),
                            message = uiState.errorMessage ?: stringResource(id = R.string.empty_favourite_list),
                        )
                    }
                } else {
                    items(favouriteCoinList) {
                        FavouriteCoinCard(
                            modifier = Modifier.padding(vertical = 4.dp),
                            name = it.name,
                            imageUrl = it.imageUrl,
                            symbol = it.symbol,
                            onClick = remember { { onViewEvent(FavouriteViewEvent.OnItemClick(it)) } },
                            isLoading = false,
                        )
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = uiState.pullToRefreshLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                backgroundColor = if (uiState.pullToRefreshLoading) Color.Red else Color.Green,
            )
        }
    }
}

@Composable
fun EventCollector(
    navigateCoinDetail: (String) -> Unit,
    uiEvent: Flow<FavouriteViewEvent>,
) {
    val navigateCoinDetailState by rememberUpdatedState { id: String -> navigateCoinDetail(id) }
    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                is FavouriteViewEvent.NavigateCoinDetail -> navigateCoinDetailState(event.item.id)
                else -> Unit
            }
        }
    }
}
