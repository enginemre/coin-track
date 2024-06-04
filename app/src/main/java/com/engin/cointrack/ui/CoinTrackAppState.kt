package com.engin.cointrack.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.engin.cointrack.home.ui.homeRoute
import com.engin.cointrack.home.ui.navigateToHome
import com.engin.cointrack.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberCoinTrackAppState(
//    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): CoinTrackAppState {
    return remember(
        navController,
        coroutineScope,
//        networkMonitor,
    ) {
        CoinTrackAppState(
            navController = navController,
            coroutineScope = coroutineScope,
//            networkMonitor = networkMonitor,
        )
    }
}

@Stable
class CoinTrackAppState(
    val navController: NavHostController,
    coroutineScope: CoroutineScope,
//    networkMonitor: NetworkMonitor,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val shouldShowBottomBar: Boolean
        @Composable get() =
            currentDestination?.hierarchy?.any { destination ->
                topLevelDestinations.any {
                    destination.route?.contains(it.route) ?: false
                }
            } ?: false

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            // TODO replace with original route
            homeRoute -> TopLevelDestination.Home
            "SearchRoute" -> TopLevelDestination.Search
            "SavedRoute" -> TopLevelDestination.Saved
            else -> null
        }

    /*val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )*/

    /**
     * Map of top level destinations to be used in the TopBar, BottomBar and NavRail. The key is the
     * route.
     */
    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    /**
     * UI logic for navigating to a top level destination in the app. Top level destinations have
     * only one copy of the destination of the back stack, and save and restore state whenever you
     * navigate to and from it.
     *
     * @param topLevelDestination: The destination the app needs to navigate to.
     */
    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.Home -> navController.navigateToHome(topLevelNavOptions)
            TopLevelDestination.Search -> Unit /*navController.navigateToBookmarks(topLevelNavOptions)*/
            TopLevelDestination.Saved -> Unit /* navController.navigateToInterests(null, topLevelNavOptions)*/
        }
    }
}
