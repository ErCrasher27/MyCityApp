package com.example.mycityapp.data.model

import android.util.Size
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

data class NavigationItemContent(
    val icon: ImageVector,
    val text: String,
    val isHomePage: Boolean = false
)