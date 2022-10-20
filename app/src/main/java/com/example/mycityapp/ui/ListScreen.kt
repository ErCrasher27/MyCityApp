package com.example.mycityapp.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycityapp.data.model.Filter
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.ui.components.PlaceCard
import com.example.mycityapp.ui.utils.MyCityNavigationType

@Composable
fun PlacesLists(
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType,
    viewModel: MyCityViewModel,
    isInHomePage: Boolean,
    placesFiltered: List<Place>,
    currentFiltersPlace: MutableList<Filter>,
    onClickFilter: (MutableList<Filter>) -> Unit,
    filterPlaces: () -> Unit,
    onValueChangeSearchPlace: (String) -> Unit
) {
    HeaderPlacesLists(
        currentFiltersPlace = currentFiltersPlace,
        filterPlaces = filterPlaces,
        onClickFilter = onClickFilter,
        onValueChangeSearchPlace = onValueChangeSearchPlace
    )
    PlacesFilter(
        navigationType = navigationType,
        viewModel = viewModel,
        isInHomePage = isInHomePage,
        placesFiltered = placesFiltered,
        filterPlaces = filterPlaces,
    )
}

@Composable
fun PlacesFilter(
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType,
    viewModel: MyCityViewModel,
    isInHomePage: Boolean,
    placesFiltered: List<Place>,
    filterPlaces: () -> Unit,
) {
    val context = LocalContext.current
    filterPlaces()
    //TODO if placesFiltered is empty do an alert dialog or something
    LazyColumn(
        modifier
            .padding(top = 18.dp)
            .animateContentSize()
    ) {
        items(placesFiltered) { place ->
            val title = stringResource(id = place.name)
            PlaceCard(
                horizontalPadding = 18,
                verticalPadding = 10,
                navigationType = navigationType,
                place = place,
                isInHomePage = isInHomePage,
                onPlaceClick = {
                    viewModel.updateCurrentDetails(it)
                },
                onClickToGo = {
                    viewModel.navigateTo(
                        context = context,
                        latLng = place.latLng,
                        title = title
                    )
                },
                loadDistance = {
                    viewModel.displayDistance(
                        context = context,
                        placeLocation = place.latLng
                    )
                },
            )
        }

    }
}