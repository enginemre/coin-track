package com.engin.cointrack.coindetail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val coinDetailRoute = "coinDetail_route"
const val idArg = "id"

class CoinDetailArgs(
    val id: String,
) {
    constructor(savedStateHandle: SavedStateHandle) : this(
        id = checkNotNull(savedStateHandle[idArg]),
    )
}

fun NavController.navigateToCoinDetail(id: String, navOptions: NavOptions? = null) {
    val route = buildString {
        append(coinDetailRoute)
        append("/$id")
    }
    this.navigate(route, navOptions)
}

fun NavGraphBuilder.coinDetail() {
    composable(
        route = coinDetailRoute.plus("/{$idArg}"),
        arguments = listOf(
            navArgument(idArg) {
                type = NavType.StringType
            },
        ),
    ) {
        CoinDetailRoute()
    }
}
