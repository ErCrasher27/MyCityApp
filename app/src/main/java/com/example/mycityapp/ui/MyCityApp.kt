package com.example.mycityapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycityapp.R
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.ui.utils.MyCityNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val navigationType: MyCityNavigationType
    val viewModel: MyCityViewModel = viewModel()
    val myCityAppUiState = viewModel.uiState.collectAsState().value

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = MyCityNavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = MyCityNavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            navigationType = MyCityNavigationType.BOTTOM_NAVIGATION
        }
    }

    when (navigationType) {

        MyCityNavigationType.BOTTOM_NAVIGATION -> {
            Scaffold(
                topBar = { TopAppBar(title = myCityAppUiState.currentTab.name) },
                content = {
                    OnlyListCategoryCards(
                        currentTab = myCityAppUiState.currentTab,
                        modifier = modifier.padding(it)
                    )
                }, bottomBar = {
                    MyCityAppBottomNavigationBar(
                        currentTab = myCityAppUiState.currentTab,
                        onTabPressed = { category: CategoryName ->
                            viewModel.updateCurrentCategory(category = category)
                        },
                    )
                }

            )
        }

        MyCityNavigationType.NAVIGATION_RAIL -> {
            Row(
                modifier = modifier
                    .fillMaxSize()
            ) {
                MyCityAppNavigationRail(
                    currentTab = myCityAppUiState.currentTab,
                    onTabPressed = { category: CategoryName ->
                        viewModel.updateCurrentCategory(category = category)
                    },
                )
                Column(
                    modifier = modifier
                        .fillMaxSize()
                ) {
                    TopAppBar(title = stringResource(id = R.string.app_name))
                    OnlyListCategoryCards(
                        currentTab = myCityAppUiState.currentTab,
                    )
                }
            }

        }

        MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER -> {

            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet(Modifier.width(240.dp)) {
                        Spacer(Modifier.height(12.dp))
                        MyCityAppNavigationDrawerContent(
                            selectedDestination = myCityAppUiState.currentTab,
                            onTabPressed = { category: CategoryName ->
                                viewModel.updateCurrentCategory(category = category)
                            }
                        )
                    }
                },
                content = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ListAndDetailsCategoryCard(currentTab = myCityAppUiState.currentTab)
                    }
                }
            )


            Column(modifier.fillMaxWidth()) {
            }
        }

    }
}


