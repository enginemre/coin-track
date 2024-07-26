package com.engin.cointrack.coindetail.ui

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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

@Suppress("MagicNumber")
fun NavGraphBuilder.coinDetail(
    navigateBack: () -> Unit,
) {
    composable(
        route = coinDetailRoute.plus("/{$idArg}"),
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(200),
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(200),
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                animationSpec = tween(200),
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                animationSpec = tween(200),
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
            )
        },
        arguments = listOf(
            navArgument(idArg) {
                type = NavType.StringType
            },
        ),
    ) {
        CoinDetailRoute(
            navigateBack = navigateBack,
        )
    }
}
