package com.engin.cointrack.home.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val homeRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.home(
    navigateBack: () -> Unit,
) {
    composable(
        route = homeRoute,
    ) {
        HomeRoute(
            navigateBack = navigateBack,
        )
    }
}
