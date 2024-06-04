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

enum class TopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
) {
    Home(
        route = "home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = R.string.home_label,
    ),
    Search(
        route = "search",
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        iconTextId = R.string.search_label,
    ),
    Saved(
        route = "saved",
        selectedIcon = Icons.Filled.Bookmark,
        unselectedIcon = Icons.Outlined.Bookmark,
        iconTextId = R.string.saved_label,
    ),
}
