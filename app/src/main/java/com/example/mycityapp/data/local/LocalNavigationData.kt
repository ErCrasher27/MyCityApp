package com.example.mycityapp.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.NavigationItemContent

object LocalNavigationData {
    val navigationsItems = listOf<NavigationItemContent>(

        NavigationItemContent(
            Icons.Default.LocalActivity,
            CategoryName.Activities.name
        ),
        NavigationItemContent(
            Icons.Default.Panorama,
            CategoryName.Panoramas.name
        ),
        NavigationItemContent(
            Icons.Default.Home,
            text = "HomePage",
            isHomePage = true
        ),
        NavigationItemContent(
            Icons.Default.Restaurant,
            CategoryName.Restaurants.name
        ),
        NavigationItemContent(
            Icons.Default.WineBar,
            CategoryName.Bar.name
        )
    )
}