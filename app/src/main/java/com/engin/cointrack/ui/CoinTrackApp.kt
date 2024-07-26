package com.engin.cointrack.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.engin.cointrack.designsystem.component.CoinTrackBackground
import com.engin.cointrack.navigation.CoinTrackNavHost

@Composable
fun CoinTrackApp(
    appState: CoinTrackAppState,
    modifier: Modifier = Modifier,
) {
    CoinTrackBackground {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .animateContentSize(
                            animationSpec = tween(200, easing = FastOutSlowInEasing),
                        ),
                ) {
                    AnimatedVisibility(
                        visible = appState.shouldShowBottomBar,
                        enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
                        exit = fadeOut() + slideOutVertically(targetOffsetY = { it }),
                    ) {
                        CoinTrackBottomBar(
                            onNavigateToDestination = appState::navigateToTopLevelDestination,
                            currentDestination = appState.currentDestination,
                        )
                    }
                }
            },
            modifier = modifier,
        ) { paddingValues ->
            CoinTrackNavHost(
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .consumeWindowInsets(paddingValues)
                    .windowInsetsPadding(WindowInsets.safeDrawing.only(WindowInsetsSides.Horizontal)),
                appState = appState,
                startDestination = appState.startDestination,
            )
        }
    }
}
