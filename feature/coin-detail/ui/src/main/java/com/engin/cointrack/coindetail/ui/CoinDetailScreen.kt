package com.engin.cointrack.coindetail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.ImageNotSupported
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.engin.cointrack.coindetail.domain.model.CoinDetail
import com.engin.cointrack.coindetail.domain.model.toCoin
import com.engin.cointrack.designsystem.component.ErrorContent
import com.engin.cointrack.designsystem.component.ThemePreviews
import com.engin.cointrack.designsystem.shimmerEffect
import com.engin.cointrack.designsystem.theme.CoinTrackTheme
import com.engin.cointrack.feature.coindetail.ui.R
import kotlinx.coroutines.launch

@Composable
fun CoinDetailRoute(
    navigateBack: () -> Unit,
    viewModel: CoinDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val coinDetailState by viewModel.coinDetailState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.refresh()
    }

    CoinDetailScreen(
        uiState = uiState,
        navigateBack = navigateBack,
        onViewEvent = viewModel::onEvent,
        coinDetailState = coinDetailState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinDetailScreen(
    uiState: CoinDetailViewState,
    navigateBack: () -> Unit,
    onViewEvent: (CoinDetailViewEvent) -> Unit,
    coinDetailState: CoinDetail?,
    modifier: Modifier = Modifier,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    SnackBar(
        showSnackBar = uiState.showSnackBar,
        message = uiState.snackBarMessage,
        snackBarHostState = snackBarHostState,
    )
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        contentWindowInsets = WindowInsets.systemBars,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = { Text(text = stringResource(id = R.string.coin_detail_title)) },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null)
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            if (uiState.loading) {
                ShimmerCoinDetailContent()
            } else {
                coinDetailState?.let {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Column {
                            SubcomposeAsyncImage(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .height(150.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                model = coinDetailState.imageUrl,
                                contentDescription = coinDetailState.name,
                                contentScale = ContentScale.Fit,
                                loading = {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .size(32.dp),
                                    )
                                },
                                error = {
                                    Icon(imageVector = Icons.Outlined.ImageNotSupported, contentDescription = coinDetailState.name)
                                },
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Column {
                                    Text(
                                        text = coinDetailState.name,
                                        style = MaterialTheme.typography.titleLarge.copy(
                                            fontWeight = FontWeight.Bold,
                                        ),
                                    )
                                    Text(
                                        text = coinDetailState.symbol,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            color = Color.Gray,
                                        ),
                                    )
                                }
                                Text(
                                    text = coinDetailState.price,
                                    style = MaterialTheme.typography.titleLarge.copy(
                                        fontWeight = FontWeight.Bold,
                                    ),
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                        ) {
                            if (coinDetailState.isFavourite) {
                                Button(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Red,
                                        contentColor = Color.White,
                                    ),
                                    onClick = remember {
                                        { onViewEvent(CoinDetailViewEvent.OnRemoveFavouriteClick(coinDetailState.toCoin())) }
                                    },
                                ) {
                                    Text(text = stringResource(R.string.remove_from_watchlist))
                                }
                            } else {
                                Button(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    onClick = remember { { onViewEvent(CoinDetailViewEvent.OnFavouriteClick(coinDetailState.toCoin())) } },
                                ) {
                                    Text(text = stringResource(R.string.add_to_watchlist))
                                }
                            }
                        }
                    }
                } ?: ErrorContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    message = uiState.errorMessage ?: stringResource(R.string.data_not_found),
                )
            }
        }
    }
}

@Composable
fun ShimmerCoinDetailContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .shimmerEffect(),
            ) {
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(16.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .shimmerEffect(),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(16.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .shimmerEffect(),
                    )
                }
                Box(
                    modifier = Modifier
                        .width(86.dp)
                        .height(16.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .shimmerEffect(),
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(24.dp))
                .shimmerEffect(),
        )
    }
}

@Composable
fun SnackBar(
    showSnackBar: Boolean,
    message: String,
    snackBarHostState: SnackbarHostState,
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(showSnackBar) {
        if (showSnackBar) {
            scope.launch {
                snackBarHostState.showSnackbar(
                    message = message,
                    duration = SnackbarDuration.Short,
                )
            }
        }
    }
}

@ThemePreviews
@Composable
private fun CoinDetailScreenPrev() {
    CoinTrackTheme {
        CoinDetailScreen(
            uiState = CoinDetailViewState(),
            onViewEvent = {},
            navigateBack = {},
            coinDetailState = CoinDetail("btc", "Bitcoin", "1", "BTC", "69,954.99 $"),
        )
    }
}

@ThemePreviews
@Composable
private fun CoinDetailScreenErrorPrev() {
    CoinTrackTheme {
        CoinDetailScreen(
            uiState = CoinDetailViewState(),
            navigateBack = {},
            onViewEvent = {},
            coinDetailState = null,
        )
    }
}

@ThemePreviews
@Composable
private fun CoinDetailShimmerPrev() {
    CoinTrackTheme {
        CoinDetailScreen(
            uiState = CoinDetailViewState(loading = true),
            navigateBack = {},
            onViewEvent = {},
            coinDetailState = null,
        )
    }
}
