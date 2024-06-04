package com.engin.cointrack.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredWidthIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.engin.cointrack.designsystem.component.ThemePreviews
import com.engin.cointrack.designsystem.theme.CoinTrackTheme
import com.engin.cointrack.navigation.TopLevelDestination

@Composable
fun CoinTrackBottomBar(
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        modifier = modifier,

    ) {
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .windowInsetsPadding(NavigationBarDefaults.windowInsets)
                .selectableGroup(),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically,
            content = {
                CoinTrackNavBarItem(
                    destination = TopLevelDestination.Home,
                    onNavigateToDestination = onNavigateToDestination,
                    currentDestination = currentDestination,
                )
                CoinTrackNavBarItem(
                    destination = TopLevelDestination.Search,
                    onNavigateToDestination = onNavigateToDestination,
                    currentDestination = currentDestination,
                )
                CoinTrackNavBarItem(
                    destination = TopLevelDestination.Saved,
                    onNavigateToDestination = onNavigateToDestination,
                    currentDestination = currentDestination,
                )
            },
        )
    }
}

@Composable
fun RowScope.CoinTrackNavBarItem(
    destination: TopLevelDestination,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
    NavigationBarItem(
        modifier = modifier.requiredWidthIn(min = 80.dp),
        selected = selected,
        colors =
        NavigationBarItemDefaults.colors(
            indicatorColor = Color.Transparent,
        ),
        onClick = { onNavigateToDestination(destination) },
        icon = {
            Icon(
                modifier = Modifier.size(22.dp),
                imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                contentDescription = stringResource(destination.iconTextId),
            )
        },
        label = {
            Text(
                stringResource(destination.iconTextId),
                style =
                MaterialTheme.typography.labelSmall.copy(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.sp,
                ),
            )
        },
    )
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

@ThemePreviews
@Composable
private fun BottomBarPreview() {
    CoinTrackTheme {
        CoinTrackBottomBar(
            onNavigateToDestination = {},
            currentDestination = null,
        )
    }
}
