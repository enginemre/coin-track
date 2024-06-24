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
import androidx.compose.material.icons.outlined.ImageNotSupported
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.engin.cointrack.designsystem.component.ErrorContent
import com.engin.cointrack.designsystem.component.ThemePreviews
import com.engin.cointrack.designsystem.shimmerEffect
import com.engin.cointrack.designsystem.theme.CoinTrackTheme
import com.engin.cointrack.feature.coindetail.ui.R

@Composable
fun CoinDetailRoute(
    viewModel: CoinDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val coinDetailState by viewModel.coinDetailState.collectAsStateWithLifecycle()

    CoinDetailScreen(
        uiState = uiState,
        coinDetailState = coinDetailState,
    )
}

@Composable
fun CoinDetailScreen(
    uiState: CoinDetailViewState,
    coinDetailState: CoinDetail?,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        contentWindowInsets = WindowInsets.systemBars,
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
                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            onClick = { /*TODO*/ },
                        ) {
                            Text(text = stringResource(R.string.add_to_watchlist))
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
                            .shimmerEffect(),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .width(50.dp)
                            .height(16.dp)
                            .shimmerEffect(),
                    )
                }
                Box(
                    modifier = Modifier
                        .width(86.dp)
                        .height(16.dp)
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

@ThemePreviews
@Composable
private fun CoinDetailScreenPrev() {
    CoinTrackTheme {
        CoinDetailScreen(
            uiState = CoinDetailViewState(),
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
            coinDetailState = null,
        )
    }
}
