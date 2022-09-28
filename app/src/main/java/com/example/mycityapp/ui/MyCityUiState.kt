package com.example.mycityapp.ui

import android.location.Location
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Place

data class MyCityUiState(
    val currentTab: CategoryName = CategoryName.Homepage,
    val currentDetails: Place? = null,
    val isInHomePage: Boolean = true,
    val currentLocation: Location? = null,
)