package com.example.mycityapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
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
import com.example.mycityapp.ui.components.DetailsPlace
import com.example.mycityapp.ui.components.DetailsPlaceCard
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
            if (myCityAppUiState.currentDetails != null) {
                DetailsPlace(
                    place = myCityAppUiState.currentDetails,
                    onClose = { viewModel.updateCurrentDetails(null) })
            }
            Scaffold(
                containerColor = MaterialTheme.colorScheme.background,
                topBar = { TopAppBar(title = myCityAppUiState.currentTab.name) },
                content = {
                    Column(modifier = modifier.padding(it)) {
                        OnlyListCards(
                            currentTab = myCityAppUiState.currentTab,
                            onCardClick = { category: CategoryName ->
                                viewModel.updateCurrentCategory(category = category)
                            },
                            viewModel = viewModel,
                        )
                    }
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
            if (myCityAppUiState.currentDetails != null) {
                DetailsPlace(
                    place = myCityAppUiState.currentDetails,
                    onClose = { viewModel.updateCurrentDetails(null) })
            }
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
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    TopAppBar(title = stringResource(id = R.string.app_name))
                    OnlyListCards(
                        currentTab = myCityAppUiState.currentTab,
                        onCardClick = { category: CategoryName ->
                            viewModel.updateCurrentCategory(category = category)
                        },
                        viewModel = viewModel,
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
                            .fillMaxHeight()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                        ) {
                            ListAndDetailsCard(
                                currentTab = myCityAppUiState.currentTab,
                                onCardClick = { category: CategoryName ->
                                    viewModel.updateCurrentCategory(category = category)
                                },
                                viewModel = viewModel,
                            )
                            Spacer(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(MaterialTheme.colorScheme.primary)
                            )
                            if (myCityAppUiState.currentDetails != null) {
                                DetailsPlaceCard(
                                    place = myCityAppUiState.currentDetails,
                                    onClose = { viewModel.updateCurrentDetails(null) },
                                    navigationType = MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER
                                )
                            }
                        }
                    }
                }
            )
        }

    }
}


