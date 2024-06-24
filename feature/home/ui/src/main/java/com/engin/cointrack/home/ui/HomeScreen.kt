package com.engin.cointrack.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.engin.cointrack.designsystem.component.ErrorContent
import com.engin.cointrack.domain.model.Coin
import com.engin.cointrack.feature.home.ui.R
import com.engin.cointrack.home.ui.component.CoinCard
import com.engin.cointrack.home.ui.component.PagingError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.IOException

@Suppress("UnusedPrivateMember")
@Composable
fun HomeRoute(
    navigateCoinDetail: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val coinList = viewModel.coinListState.collectAsLazyPagingItems()

    EventCollector(
        navigateCoinDetail = navigateCoinDetail,
        uiEvent = viewModel.uiEvent,
    )

    HomeScreen(
        uiState = uiState,
        coinList = coinList,
        onViewEvent = viewModel::onEvent,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    uiState: HomeViewState,
    coinList: LazyPagingItems<Coin>,
    onViewEvent: (HomeViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    var isPullToRefreshLoading by remember { mutableStateOf(false) }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = isPullToRefreshLoading,
        onRefresh = {
            isPullToRefreshLoading = true
            coinList.refresh()
        },
    )
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(snackBarHostState) },
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pullRefresh(pullRefreshState)
                .padding(paddingValues)
                .padding(WindowInsets.statusBars.asPaddingValues()),
        ) {
            if ((coinList.loadState.refresh as? LoadState.Error)?.error is IOException) {
                ErrorSnackBar(coinList = coinList, snackBarHostState = snackBarHostState)
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp),
            ) {
                when (coinList.loadState.refresh) {
                    LoadState.Loading -> {
                        items(shimmerList) { item ->
                            CoinCard(
                                modifier = Modifier.padding(vertical = 4.dp),
                                name = item.name,
                                imageUrl = item.imageUrl,
                                symbol = item.symbol,
                                price = item.priceStr,
                                onClick = remember { { onViewEvent(HomeViewEvent.OnItemClick(item.id)) } },
                                isLoading = true,
                            )
                        }
                    }
                    else -> {
                        isPullToRefreshLoading = false
                        if (uiState.errorMessage.isNullOrEmpty()) {
                            items(
                                count = coinList.itemCount,
                                key = coinList.itemKey(),
                                contentType = coinList.itemContentType(),
                            ) {
                                val item = coinList[it]
                                item?.let {
                                    CoinCard(
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        name = item.name,
                                        imageUrl = item.imageUrl,
                                        symbol = item.symbol,
                                        price = item.priceStr,
                                        onClick = remember { { onViewEvent(HomeViewEvent.OnItemClick(it.id)) } },
                                        isLoading = false,
                                    )
                                }
                            }
                            when (val appendStatus = coinList.loadState.append) {
                                is LoadState.NotLoading -> Unit
                                is LoadState.Error -> {
                                    item {
                                        PagingError(
                                            modifier = Modifier.padding(12.dp),
                                            message = appendStatus.error.message ?: "Error",
                                            onRetryClick = { coinList.retry() },
                                        )
                                    }
                                }
                                LoadState.Loading -> item {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(12.dp),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        CircularProgressIndicator()
                                    }
                                }
                            }
                        } else {
                            item {
                                ErrorContent(
                                    modifier = Modifier.fillParentMaxSize(),
                                    message = uiState.errorMessage,
                                )
                            }
                        }
                    }
                }
            }
            PullRefreshIndicator(
                refreshing = isPullToRefreshLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter),
                backgroundColor = if (isPullToRefreshLoading) Color.Red else Color.Green,
            )
        }
    }
}

@Composable
fun ErrorSnackBar(
    coinList: LazyPagingItems<Coin>,
    snackBarHostState: SnackbarHostState,
) {
    val scope = rememberCoroutineScope()
    val connectionString = stringResource(id = R.string.connection_error_message)
    val retryString = stringResource(id = R.string.retry)
    LaunchedEffect((coinList.loadState.refresh as? LoadState.Error)?.error is IOException) {
        scope.launch {
            val action = snackBarHostState.showSnackbar(
                message = connectionString,
                actionLabel = retryString,
                duration = SnackbarDuration.Indefinite,
                withDismissAction = true,
            )
            when (action) {
                SnackbarResult.ActionPerformed -> {
                    coinList.refresh()
                }

                else -> Unit
            }
        }
    }
}

@Composable
fun EventCollector(
    navigateCoinDetail: (String) -> Unit,
    uiEvent: Flow<HomeViewEvent>,
) {
    val navigateCoinDetailState by rememberUpdatedState { id: String -> navigateCoinDetail(id) }
    LaunchedEffect(Unit) {
        uiEvent.collect { event ->
            when (event) {
                is HomeViewEvent.NavigateToDetail -> navigateCoinDetailState(event.id)
                else -> Unit
            }
        }
    }
}
