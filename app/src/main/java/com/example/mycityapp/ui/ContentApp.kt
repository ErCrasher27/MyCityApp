package com.example.mycityapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Filter
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.ui.utils.MyCityNavigationType

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun OnlyListCards(
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType,
    currentTab: CategoryName,
    viewModel: MyCityViewModel,
    isInHomePage: Boolean,
    currentFiltersPlace: MutableList<Filter>,
    placesFiltered: List<Place>,
    expandedFilters: Boolean,
    onExpandFilters: (Boolean) -> Unit,
    filterPlaces: () -> Unit,
    onClickFilter: (MutableList<Filter>) -> Unit,
    onCardClick: (CategoryName) -> Unit,
) {
    if (currentTab.name == CategoryName.Homepage.name) {
        Column(
            Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        )
        {
            CategoriesHorizontalListsWithHeader(title = "Categories", onCardClick = onCardClick)

            BestPlacesHorizontalListWithHeader(
                navigationType = navigationType,
                title = "Considering the Weather",
                viewModel = viewModel,
                isInHomePage = isInHomePage,
            )
        }
    } else {
        PlacesLists(
            navigationType = navigationType,
            viewModel = viewModel,
            isInHomePage = isInHomePage,
            currentFiltersPlace = currentFiltersPlace,
            expandedFilters = expandedFilters,
            placesFiltered = placesFiltered,
            filterPlaces = filterPlaces,
            onExpandFilters = onExpandFilters,
            onClickFilter = onClickFilter,
        )
    }
}

@Composable
fun ListAndDetailsCard(
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType,
    currentTab: CategoryName,
    viewModel: MyCityViewModel,
    isInHomePage: Boolean,
    currentFiltersPlace: MutableList<Filter>,
    placesFiltered: List<Place>,
    expandedFilters: Boolean,
    onExpandFilters: (Boolean) -> Unit,
    filterPlaces: () -> Unit,
    onClickFilter: (MutableList<Filter>) -> Unit,
    onCardClick: (CategoryName) -> Unit,
) {
    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.width(600.dp)) {
        if (currentTab.name == CategoryName.Homepage.name) {
            Column(
                Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                CategoriesHorizontalListsWithHeader(
                    title = "Categories",
                    onCardClick = onCardClick,
                )
                BestPlacesHorizontalListWithHeader(
                    navigationType = navigationType,
                    title = "Best Ratings",
                    viewModel = viewModel,
                    isInHomePage = isInHomePage,
                )
            }
        } else {
            PlacesLists(
                navigationType = navigationType,
                viewModel = viewModel,
                isInHomePage = isInHomePage,
                currentFiltersPlace = currentFiltersPlace,
                expandedFilters = expandedFilters,
                placesFiltered = placesFiltered,
                filterPlaces = filterPlaces,
                onExpandFilters = onExpandFilters,
                onClickFilter = onClickFilter,
            )
        }
    }
}





