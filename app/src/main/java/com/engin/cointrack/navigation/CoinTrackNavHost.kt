package com.engin.cointrack.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.engin.cointrack.coindetail.ui.coinDetail
import com.engin.cointrack.coindetail.ui.navigateToCoinDetail
import com.engin.cointrack.feature.favourite.ui.favourite
import com.engin.cointrack.home.ui.home
import com.engin.cointrack.home.ui.navigateToHome
import com.engin.cointrack.search.ui.search
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
            navigateHome = navController::navigateToHome,
        )
        home(
            navigateToCoinDetail = navController::navigateToCoinDetail,
        )
        search()
        favourite(
            navigateCoinDetail = navController::navigateToCoinDetail,
        )
        coinDetail(
            navigateBack = navController::navigateUp,
        )
    }
}
