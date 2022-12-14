package com.example.mycityapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycityapp.R
import com.example.mycityapp.data.local.LocalNavigationData.navigationsItems
import com.example.mycityapp.data.model.CategoryName

@Composable
fun MyCityAppBottomNavigationBar(
    modifier: Modifier = Modifier,
    currentTab: CategoryName,
    onTabPressed: (CategoryName) -> Unit
) {
    val bottomNavigationContentDescription = stringResource(id = R.string.navigation_bottom)

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxWidth()
            .testTag(bottomNavigationContentDescription)
    ) {
        for (navItem in navigationsItems) {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text.name,
                    )
                },
                selected = currentTab == navItem.text,
                onClick = { onTabPressed(navItem.text) },
            )
        }
    }
}

@Composable
fun MyCityAppNavigationRail(
    modifier: Modifier = Modifier,
    currentTab: CategoryName,
    onTabPressed: (CategoryName) -> Unit
) {
    val railNavigationContentDescription = stringResource(id = R.string.navigation_rail)
    NavigationRail(
        modifier = modifier
            .fillMaxHeight()
            .testTag(railNavigationContentDescription),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        for (navItem in navigationsItems) {
            NavigationRailItem(
                selected = currentTab == navItem.text,
                onClick = { onTabPressed(navItem.text) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text.name,
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppNavigationDrawerContent(
    modifier: Modifier = Modifier,
    selectedDestination: CategoryName,
    onTabPressed: (CategoryName) -> Unit
) {
    val drawerNavigationContentDescription = stringResource(id = R.string.navigation_drawer)
    NavigationRail(
        modifier = modifier
            .fillMaxHeight()
            .testTag(drawerNavigationContentDescription),

        containerColor = MaterialTheme.colorScheme.surfaceVariant,
    ) {
        NavigationDrawerHeader(modifier)
        for (navItem in navigationsItems) {
            NavigationDrawerItem(
                selected = selectedDestination == navItem.text,
                label = {
                    Text(
                        text = navItem.text.name,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text.name,
                        modifier = Modifier.testTag(navItem.text.name)
                    )
                },
                onClick = { onTabPressed(navItem.text) }
            )
        }
    }
}

@Composable
private fun NavigationDrawerHeader(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.app_name), style = MaterialTheme.typography.titleLarge
        )
        Icon(
            imageVector = Icons.Default.TravelExplore,
            contentDescription = stringResource(R.string.app_name),
        )
    }
}
