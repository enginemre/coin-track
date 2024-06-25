package com.engin.cointrack.feature.favourite.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ImageNotSupported
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.engin.cointrack.designsystem.shimmerEffect

@Composable
fun FavouriteCoinCard(
    name: String,
    imageUrl: String,
    symbol: String,
    onClick: () -> Unit,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    if (isLoading) {
        ShimmerCoinCard(modifier = modifier)
    } else {
        Card(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
            onClick = onClick,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row {
                    SubcomposeAsyncImage(
                        modifier = Modifier.size(50.dp),
                        contentScale = ContentScale.Fit,
                        model = imageUrl,
                        contentDescription = name,
                        loading = {
                            CircularProgressIndicator()
                        },
                        error = {
                            Icon(imageVector = Icons.Outlined.ImageNotSupported, contentDescription = name)
                        },
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "$symbol ",
                            style = MaterialTheme.typography.bodySmall,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerCoinCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = {},
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .size(50.dp)
                        .shimmerEffect(),
                ) {}
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .size(100.dp, 15.dp)
                            .shimmerEffect(),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .size(50.dp, 15.dp)
                            .shimmerEffect(),
                    )
                }
            }
        }
    }
}
