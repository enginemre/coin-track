package com.engin.cointrack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.engin.cointrack.ui.CoinTrackAppState
import com.engin.cointrack.ui.login.login
import com.engin.cointrack.ui.login.loginRoute

@Composable
fun CoinTrackNavHost(
    appState: CoinTrackAppState,
    modifier: Modifier = Modifier,
    startDestination: String = loginRoute,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        login(
            navigateBack = navController::navigateUp,
        )
    }
}
