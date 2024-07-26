package com.engin.cointrack.search.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.engin.cointrack.core.model.Coin
import com.engin.cointrack.core.ui.component.CoinCard
import com.engin.cointrack.designsystem.component.ErrorContent
import com.engin.cointrack.feature.search.ui.R

@Composable
fun SearchRoute(
    navigateCoinDetail: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val coins = viewModel.coins.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is SearchViewEvent.OnItemClick -> {
                    navigateCoinDetail(event.item.id)
                }

                else -> {}
            }
        }
    }

    SearchScreen(
        uiState = uiState,
        coins = coins,
        onViewEvent = viewModel::onEvent,
    )
}

@Composable
fun SearchScreen(
    uiState: SearchViewState,
    coins: LazyPagingItems<Coin>,
    onViewEvent: (SearchViewEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.systemBars,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .windowInsetsPadding(NavigationBarDefaults.windowInsets),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                value = uiState.searchText,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Search,
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    IconButton(onClick = remember { { onViewEvent(SearchViewEvent.OnClearText) } }) {
                        Icon(
                            imageVector = Icons.Outlined.Cancel,
                            contentDescription = null,
                        )
                    }
                },
                onValueChange = remember { { onViewEvent(SearchViewEvent.OnSearchTextChange(it)) } },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_placeholder),
                    )
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text,
                ),
                shape = RoundedCornerShape(18.dp),
            )
            Spacer(modifier = Modifier.height(12.dp))
            when (coins.loadState.refresh) {
                is LoadState.Error -> ErrorContent(
                    modifier = Modifier.fillMaxSize(),
                    message = stringResource(id = R.string.search_error),
                )

                LoadState.Loading -> Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    CircularProgressIndicator()
                }

                is LoadState.NotLoading -> {
                    if (coins.itemCount == 0) {
                        ErrorContent(
                            modifier = Modifier.fillMaxSize(),
                            message = stringResource(id = R.string.search_error),
                        )
                    }
                    LazyColumn(
                        contentPadding = PaddingValues(12.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        items(
                            count = coins.itemCount,
                            key = coins.itemKey(),
                            contentType = coins.itemContentType(),
                        ) {
                            val item = coins[it]
                            item?.let {
                                CoinCard(
                                    name = item.name,
                                    symbol = item.symbol,
                                    price = item.priceStr,
                                    imageUrl = item.imageUrl,
                                    isLoading = false,
                                    onClick = remember { { onViewEvent(SearchViewEvent.OnItemClick(item)) } },
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
