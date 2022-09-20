package com.example.mycityapp.ui

import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Place

data class MyCityUiState(
    val currentTab: CategoryName = CategoryName.homepage,
    val currentDetails: Place? = null
)