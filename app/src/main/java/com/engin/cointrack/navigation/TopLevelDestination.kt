package com.engin.cointrack.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.engin.cointrack.R
import com.engin.cointrack.feature.favourite.ui.favouriteRoute
import com.engin.cointrack.home.ui.homeRoute
import com.engin.cointrack.search.ui.searchRoute

enum class TopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
) {
    Home(
        route = homeRoute,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = R.string.home_label,
    ),
    Search(
        route = searchRoute,
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        iconTextId = R.string.search_label,
    ),
    Saved(
        route = favouriteRoute,
        selectedIcon = Icons.Filled.Bookmark,
        unselectedIcon = Icons.Outlined.Bookmark,
        iconTextId = R.string.saved_label,
    ),
}
