package com.engin.cointrack.feature.favourite.ui

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val favouriteRoute = "favourite_route"
const val shouldRefreshArg = "should_refresh"

fun NavController.navigateToFavourite(navOptions: NavOptions? = null) {
    this.navigate(favouriteRoute, navOptions)
}

fun NavController.navigateBackWithArgumentFromCoinDetail(shouldRefresh: Boolean) {
    previousBackStackEntry?.setShouldRefresh(shouldRefresh)
    popBackStack()
}

fun NavGraphBuilder.favourite(
    navigateCoinDetail: (String) -> Unit,
) {
    composable(
        route = favouriteRoute,
    ) { navBackStackEntry ->
        FavouriteRoute(
            shouldRefresh = navBackStackEntry.getShouldRefresh(),
            navigateCoinDetail = navigateCoinDetail,
        )
    }
}

fun NavBackStackEntry.getShouldRefresh(): Boolean {
    return this.savedStateHandle.get<Boolean>(shouldRefreshArg)?.also {
        this.savedStateHandle.remove<Boolean>(shouldRefreshArg)
    } ?: false
}

fun NavBackStackEntry.setShouldRefresh(value: Boolean) {
    return this.savedStateHandle.set(shouldRefreshArg, value)
}
