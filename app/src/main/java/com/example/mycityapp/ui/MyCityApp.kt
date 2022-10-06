package com.example.mycityapp.ui

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.ui.components.DetailsPlace
import com.example.mycityapp.ui.components.DetailsPlaceCard
import com.example.mycityapp.ui.utils.MyCityNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    RequestPermissions(
        context = context,
        permission = Manifest.permission.CALL_PHONE,
        namePermission = "Phone"
    )
    RequestPermissions(
        context = context,
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        namePermission = "Location"
    )

    val viewModel: MyCityViewModel = viewModel()
    val myCityAppUiState = viewModel.uiState.collectAsState().value
    viewModel.getCurrentLocation(context)
    val navigationType: MyCityNavigationType = when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            MyCityNavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            MyCityNavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            MyCityNavigationType.BOTTOM_NAVIGATION
        }
    }

    when (navigationType) {
        MyCityNavigationType.BOTTOM_NAVIGATION -> {
            if (myCityAppUiState.currentDetails != null) {
                val context = LocalContext.current
                val title = stringResource(id = myCityAppUiState.currentDetails.name)
                DetailsPlace(
                    place = myCityAppUiState.currentDetails,
                    onClose = { viewModel.updateCurrentDetails(null) },
                    onClickToCall = {
                        viewModel.callPlace(
                            context = context,
                            phoneNumber = myCityAppUiState.currentDetails.phonePlace
                        )
                    },
                    onClickToGo = {
                        viewModel.navigateTo(
                            context = context,
                            latLng = myCityAppUiState.currentDetails.latLng,
                            title = title
                        )
                    }
                )
            }
            Scaffold(
                containerColor = MaterialTheme.colorScheme.background,
                topBar = { TopAppBar(title = myCityAppUiState.currentTab.name) },
                content = {
                    Column(modifier = modifier.padding(it)) {
                        val context = LocalContext.current
                        OnlyListCards(
                            navigationType = navigationType,
                            viewModel = viewModel,
                            currentTab = myCityAppUiState.currentTab,
                            isInHomePage = myCityAppUiState.isInHomePage,
                            currentFiltersPlace = myCityAppUiState.currentFilters,
                            placesFiltered = myCityAppUiState.placesFiltered,
                            onClickFilter = { viewModel.updateCurrentFiltersPlace(it) },
                            filterPlaces = { viewModel.filterPlace(context = context) },
                            onValueChangeSearchPlace = { viewModel.updateSearchNamePlace(it) },
                            onCardClick = { category: CategoryName ->
                                viewModel.updateCurrentCategory(category = category)
                            },
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
                val context = LocalContext.current
                val title = stringResource(id = myCityAppUiState.currentDetails.name)
                DetailsPlace(
                    place = myCityAppUiState.currentDetails,
                    onClose = { viewModel.updateCurrentDetails(null) },
                    onClickToCall = {
                        viewModel.callPlace(
                            context = context,
                            phoneNumber = myCityAppUiState.currentDetails.phonePlace
                        )
                    },
                    onClickToGo = {
                        viewModel.navigateTo(
                            context = context,
                            latLng = myCityAppUiState.currentDetails.latLng,
                            title = title
                        )
                    })
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
                    val context = LocalContext.current
                    TopAppBar(title = myCityAppUiState.currentTab.name)
                    OnlyListCards(
                        navigationType = navigationType,
                        viewModel = viewModel,
                        currentTab = myCityAppUiState.currentTab,
                        isInHomePage = myCityAppUiState.isInHomePage,
                        currentFiltersPlace = myCityAppUiState.currentFilters,
                        placesFiltered = myCityAppUiState.placesFiltered,
                        onClickFilter = { viewModel.updateCurrentFiltersPlace(it) },
                        filterPlaces = { viewModel.filterPlace(context) },
                        onValueChangeSearchPlace = { viewModel.updateSearchNamePlace(it) },
                        onCardClick = { category: CategoryName ->
                            viewModel.updateCurrentCategory(category = category)
                        }
                    )
                }
            }

        }

        MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet(Modifier.width(240.dp)) {
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
                            val context = LocalContext.current
                            ListAndDetailsCard(
                                navigationType = navigationType,
                                viewModel = viewModel,
                                currentTab = myCityAppUiState.currentTab,
                                isInHomePage = myCityAppUiState.isInHomePage,
                                currentFiltersPlace = myCityAppUiState.currentFilters,
                                placesFiltered = myCityAppUiState.placesFiltered,
                                onClickFilter = { viewModel.updateCurrentFiltersPlace(it) },
                                filterPlaces = { viewModel.filterPlace(context) },
                                onValueChangeSearchPlace = { viewModel.updateSearchNamePlace(it) },
                                onCardClick = { category: CategoryName ->
                                    viewModel.updateCurrentCategory(category = category)
                                },
                            )
                            Spacer(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                            )
                            if (myCityAppUiState.currentDetails != null) {
                                val context = LocalContext.current
                                val title =
                                    stringResource(id = myCityAppUiState.currentDetails.name)
                                DetailsPlaceCard(
                                    navigationType = MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER,
                                    place = myCityAppUiState.currentDetails,
                                    onClose = { viewModel.updateCurrentDetails(null) },
                                    onClickToCall = {
                                        viewModel.callPlace(
                                            context = context,
                                            phoneNumber = myCityAppUiState.currentDetails.phonePlace
                                        )
                                    },
                                    onClickToGo = {
                                        viewModel.navigateTo(
                                            context = context,
                                            latLng = myCityAppUiState.currentDetails.latLng,
                                            title = title
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}

/*
//Function that will block the screen orientation of the app
@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}*/
