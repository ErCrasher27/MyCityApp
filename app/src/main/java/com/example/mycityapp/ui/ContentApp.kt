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
    filterPlaces: () -> Unit,
    onClickFilter: (MutableList<Filter>) -> Unit,
    onCardClick: (CategoryName) -> Unit,
    onValueChangeSearchPlace: (String) -> Unit
) {
    if (currentTab.name == CategoryName.Homepage.name) {
        Column(
            Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        )
        {
            CategoriesHorizontalListsWithHeader(title = "categories", onCardClick = onCardClick)
            WeatherWithHeader(
                navigationType = navigationType,
                title = "considering the weather...",
                viewModel = viewModel,
                isInHomePage = isInHomePage,
            )
            BestPlacesHorizontalListWithHeader(
                navigationType = navigationType,
                title = "best ratings",
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
            placesFiltered = placesFiltered,
            filterPlaces = filterPlaces,
            onClickFilter = onClickFilter,
            onValueChangeSearchPlace = onValueChangeSearchPlace
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
    filterPlaces: () -> Unit,
    onClickFilter: (MutableList<Filter>) -> Unit,
    onCardClick: (CategoryName) -> Unit,
    onValueChangeSearchPlace: (String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.width(600.dp)) {
        if (currentTab.name == CategoryName.Homepage.name) {
            Column(
                Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                CategoriesHorizontalListsWithHeader(
                    title = "categories",
                    onCardClick = onCardClick,
                )
                BestPlacesHorizontalListWithHeader(
                    navigationType = navigationType,
                    title = "best ratings",
                    viewModel = viewModel,
                    isInHomePage = isInHomePage,
                )
            }
        } else {
            Column {
                PlacesLists(
                    navigationType = navigationType,
                    viewModel = viewModel,
                    isInHomePage = isInHomePage,
                    currentFiltersPlace = currentFiltersPlace,
                    placesFiltered = placesFiltered,
                    filterPlaces = filterPlaces,
                    onClickFilter = onClickFilter,
                    onValueChangeSearchPlace = onValueChangeSearchPlace
                )
            }
        }
    }
}





