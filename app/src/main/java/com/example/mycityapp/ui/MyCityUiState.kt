package com.example.mycityapp.ui

import android.location.Location
import androidx.compose.runtime.MutableState
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Filter
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.data.remote.dto.PostRequest
import com.example.mycityapp.data.remote.dto.PostResponse
import kotlinx.coroutines.flow.MutableStateFlow

data class MyCityUiState(
    val currentTab: CategoryName = CategoryName.Homepage,
    val currentDetails: Place? = null,
    val isInHomePage: Boolean = true,
    val currentLocation: Location? = null,
    val searchPlaceName: String = "",
    var expandedFilters: Boolean = false,
    var currentFilters: MutableList<Filter> = mutableListOf(),
    var placesFiltered: List<Place> = listOf(),
    var weather: PostResponse? = null
)