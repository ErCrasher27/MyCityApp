package com.example.mycityapp.ui

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mycityapp.ui.utils.MyCityContentType
import com.example.mycityapp.ui.utils.MyCityNavigationType

@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    val navigationType: MyCityNavigationType
    val contentType: MyCityContentType
    val viewModel: MyCityViewModel = viewModel()
    val replyUiState = viewModel.uiState.collectAsState().value

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = MyCityNavigationType.BOTTOM_NAVIGATION
            contentType = MyCityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = MyCityNavigationType.NAVIGATION_RAIL
            contentType = MyCityContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = MyCityContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = MyCityNavigationType.BOTTOM_NAVIGATION
            contentType = MyCityContentType.LIST_ONLY
        }
    }
}