package com.example.mycityapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycityapp.R
import com.example.mycityapp.data.local.LocalNavigationData.navigationsItems
import com.example.mycityapp.data.model.CategoryName

@Composable
fun MyCityAppBottomNavigationBar(
    currentTab: CategoryName,
    onTabPressed: (CategoryName) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier.fillMaxWidth()
    ) {
        for (navItem in navigationsItems) {

            //val tint = MaterialTheme.colorScheme.onPrimaryContainer
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text.name,
                        tint = MaterialTheme.colorScheme.primary
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
    currentTab: CategoryName,
    onTabPressed: (CategoryName) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier.fillMaxHeight(),
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        for (navItem in navigationsItems) {
            NavigationRailItem(
                selected = currentTab == navItem.text,
                onClick = { onTabPressed(navItem.text) },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text.name,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppNavigationDrawerContent(
    selectedDestination: CategoryName,
    onTabPressed: (CategoryName) -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationRail(
        modifier = modifier.fillMaxHeight(),
        containerColor = MaterialTheme.colorScheme.surface,
    ) {
        NavigationDrawerHeader(modifier)
        for (navItem in navigationsItems) {
            NavigationDrawerItem(
                selected = selectedDestination == navItem.text,
                label = {
                    Text(
                        text = navItem.text.name,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.text.name,
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                onClick = { onTabPressed(navItem.text) }
            )
        }
    }
}

@Composable
private fun NavigationDrawerHeader(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = stringResource(R.string.app_name))
        Icon(
            imageVector = Icons.Default.TravelExplore,
            contentDescription = stringResource(R.string.app_name),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
