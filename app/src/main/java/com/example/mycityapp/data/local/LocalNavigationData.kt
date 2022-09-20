package com.example.mycityapp.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.NavigationItemContent

object LocalNavigationData {
    val navigationsItems = listOf<NavigationItemContent>(
        NavigationItemContent(
            icon = Icons.Default.LocalActivity,
            text = CategoryName.activities
        ),
        NavigationItemContent(
            icon = Icons.Default.Panorama,
            text = CategoryName.panoramas
        ),
        NavigationItemContent(
            icon = Icons.Default.Home,
            text = CategoryName.homepage,
        ),
        NavigationItemContent(
            icon = Icons.Default.Restaurant,
            text = CategoryName.restaurants
        ),
        NavigationItemContent(
            icon = Icons.Default.WineBar,
            text = CategoryName.bar
        )
    )
}