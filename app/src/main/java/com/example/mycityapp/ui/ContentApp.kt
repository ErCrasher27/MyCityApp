package com.example.mycityapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.mycityapp.data.local.LocalCategoryData.categories
import com.example.mycityapp.data.local.LocalPlaceData
import com.example.mycityapp.data.local.LocalPlaceData.places
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Rate
import com.example.mycityapp.ui.components.CategoryCard
import com.example.mycityapp.ui.components.HeaderListCard
import com.example.mycityapp.ui.components.PlaceCard
import com.example.mycityapp.ui.utils.MyCityNavigationType
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun OnlyListCards(
    currentTab: CategoryName,
    onCardClick: (CategoryName) -> Unit,
    viewModel: MyCityViewModel,
    isInHomePage: Boolean,
    navigationType: MyCityNavigationType
) {
    if (currentTab.name == CategoryName.Homepage.name) {
        Column(
            Modifier.verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        )
        {
            CategoriesHorizontalListsWithHeader(onCardClick = onCardClick, title = "Categories")
            BestPlacesHorizontalListWithHeader(
                title = "Considering the Weather",
                viewModel = viewModel,
                isInHomePage = isInHomePage,
                navigationType = navigationType,
            )
            BestPlacesHorizontalListWithHeader(
                title = "Best Ratings",
                viewModel = viewModel,
                isInHomePage = isInHomePage,
                navigationType = navigationType,
            )
        }
    } else {
        PlacesLists(
            currentTab = currentTab,
            viewModel = viewModel,
            isInHomePage = isInHomePage,
            navigationType = navigationType,
        )
    }
}


@Composable
fun ListAndDetailsCard(
    currentTab: CategoryName,
    onCardClick: (CategoryName) -> Unit,
    viewModel: MyCityViewModel,
    isInHomePage: Boolean,
    navigationType: MyCityNavigationType,
) {
    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.width(600.dp)) {
        if (currentTab.name == CategoryName.Homepage.name) {
            Column(
                Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                CategoriesHorizontalListsWithHeader(
                    onCardClick = onCardClick,
                    title = "Categories"
                )
                BestPlacesHorizontalListWithHeader(
                    title = "Best Ratings",
                    viewModel = viewModel,
                    isInHomePage = isInHomePage,
                    navigationType = navigationType,
                )
            }
        } else {
            PlacesLists(
                currentTab = currentTab,
                viewModel = viewModel,
                isInHomePage = isInHomePage,
                navigationType = navigationType
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CategoriesHorizontalListsWithHeader(
    onCardClick: (CategoryName) -> Unit,
    modifier: Modifier = Modifier,
    title: String
) {
    Column {
        HeaderListCard(title = title)
        HorizontalPager(
            count = categories.size,
            modifier,
            contentPadding = PaddingValues(horizontal = 60.dp)
        ) { page ->

            Card(
                onClick = { onCardClick(categories[page].nameCategory) },
                Modifier
                    .width(600.dp)
                    .height(250.dp)
                    .testTag(categories[page].nameCategory.name)
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                colors = CardDefaults.cardColors(
                    containerColor = Color(categories[page].backgroundColor)
                ),
                shape = MaterialTheme.shapes.large
            ) {
                CategoryCard(
                    category = categories[page],
                )
            }
        }
    }

}

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BestPlacesHorizontalListWithHeader(
    modifier: Modifier = Modifier,
    title: String,
    viewModel: MyCityViewModel,
    isInHomePage: Boolean,
    navigationType: MyCityNavigationType
) {
    Column {
        val bestPlacesWithFourOrMoreStars =
            places.filter { place -> place.ratingPlace == Rate.STAR4 || place.ratingPlace == Rate.STAR5 }
        HeaderListCard(title = title)
        HorizontalPager(
            count = bestPlacesWithFourOrMoreStars.size,
            modifier,
            contentPadding = PaddingValues(horizontal = 40.dp)
        ) { page ->
            Card(
                Modifier
                    .width(500.dp)
                    .graphicsLayer {
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    },
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                shape = MaterialTheme.shapes.large
            ) {
                val context = LocalContext.current
                val title = stringResource(id = bestPlacesWithFourOrMoreStars[page].name)
                PlaceCard(
                    place = bestPlacesWithFourOrMoreStars[page],
                    onClickToGo = {
                        viewModel.navigateTo(
                            context = context,
                            latLng = bestPlacesWithFourOrMoreStars[page].latLng,
                            title = title
                        )
                    },
                    loadDistance = {
                        viewModel.displayDistance(
                            placeLocation = bestPlacesWithFourOrMoreStars[page].latLng,
                            context = context
                        )
                    },
                    onPlaceClick = {
                        viewModel.updateCurrentDetails(it)
                    },
                    horizontalPadding = 0,
                    verticalPadding = 0,
                    isInHomePage = isInHomePage,
                    navigationType = navigationType,
                )
            }
        }
    }
}

@Composable
fun PlacesLists(
    currentTab: CategoryName,
    viewModel: MyCityViewModel,
    modifier: Modifier = Modifier,
    isInHomePage: Boolean,
    navigationType: MyCityNavigationType
) {
    val context = LocalContext.current
    LazyColumn(modifier) {
        items(LocalPlaceData.places.filter { it.category.nameCategory.name == currentTab.name }) { place ->
            val title = stringResource(id = place.name)
            PlaceCard(
                place = place,
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
                        placeLocation = place.latLng,
                        context = context
                    )
                },
                horizontalPadding = 18,
                verticalPadding = 18,
                isInHomePage = isInHomePage,
                navigationType = navigationType,
            )
        }
    }
}