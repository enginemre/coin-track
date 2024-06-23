package com.engin.cointrack.home.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.engin.cointrack.designsystem.component.ErrorContent
import com.engin.cointrack.home.ui.R

@Composable
fun PagingError(
    message: String,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(12.dp),
        verticalArrangement = Arrangement.Center,
    ) {
        ErrorContent(
            modifier = Modifier.fillMaxWidth(),
            message = message,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onRetryClick,
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.retry), modifier = Modifier.align(Alignment.Center))
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = stringResource(id = R.string.retry),
                    modifier = Modifier.align(Alignment.CenterEnd),
                )
            }
        }
    }
}
