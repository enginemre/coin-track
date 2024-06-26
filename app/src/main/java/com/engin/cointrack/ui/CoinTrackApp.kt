package com.engin.cointrack.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.engin.cointrack.designsystem.component.CoinTrackBackground
import com.engin.cointrack.navigation.CoinTrackNavHost
import com.engin.cointrack.navigation.TopLevelDestination

@Composable
fun CoinTrackApp(
    appState: CoinTrackAppState,
    modifier: Modifier = Modifier,
) {
    CoinTrackBackground {
        Scaffold(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    // TODO add bottom bar
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
            )
        }
    }
}

@Suppress("UnusedPrivateMember")
private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
