package com.example.mycityapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
) {
    if (currentTab.name == CategoryName.Homepage.name) {
        LazyColumn {
            items(1) {
                Spacer(modifier = Modifier.height(10.dp))
                CategoriesHorizontalListsWithHeader(onCardClick = onCardClick, title = "Categories")
                Spacer(modifier = Modifier.height(40.dp))
                BestPlacesHorizontalListWithHeader(
                    onCardClick = onCardClick,
                    title = "Best Ratings",
                    viewModel = viewModel
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

        }

    } else {
        PlacesLists(currentTab = currentTab, viewModel = viewModel)
    }
}


@Composable
fun ListAndDetailsCard(
    currentTab: CategoryName,
    onCardClick: (CategoryName) -> Unit,
    viewModel: MyCityViewModel,
) {
    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.width(600.dp)) {
        if (currentTab.name == CategoryName.Homepage.name) {
            LazyColumn {
                items(1) {
                    Spacer(modifier = Modifier.height(10.dp))
                    CategoriesHorizontalListsWithHeader(
                        onCardClick = onCardClick,
                        title = "Categories"
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    BestPlacesHorizontalListWithHeader(
                        onCardClick = onCardClick,
                        title = "Best Ratings",
                        viewModel = viewModel
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        } else {
            PlacesLists(currentTab = currentTab, viewModel = viewModel)
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

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
@Composable
fun BestPlacesHorizontalListWithHeader(
    onCardClick: (CategoryName) -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    viewModel: MyCityViewModel,
) {
    val bestPlacesWithFourOrMoreStars =
        places.filter { place -> place.ratingPlace == Rate.STAR4 || place.ratingPlace == Rate.STAR5 }
    HeaderListCard(title = title)
    HorizontalPager(
        count = bestPlacesWithFourOrMoreStars.size,
        modifier,
        contentPadding = PaddingValues(horizontal = 35.dp)
    ) { page ->
        Card(
            onClick = { onCardClick(bestPlacesWithFourOrMoreStars[page].category.nameCategory) },
            Modifier
                .width(600.dp)
                .height(190.dp)
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
            PlaceCard(
                place = bestPlacesWithFourOrMoreStars[page], onPlaceClick = {
                    viewModel.updateCurrentDetails(it)
                }, horizontalPadding = 0,
                verticalPadding = 0,
                isInHomePage = true
            )
        }
    }
}

@Composable
fun PlacesLists(
    currentTab: CategoryName,
    viewModel: MyCityViewModel,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(LocalPlaceData.places.filter { it.category.nameCategory.name == currentTab.name }) { place ->
            PlaceCard(
                place = place,
                onPlaceClick = {
                    viewModel.updateCurrentDetails(it)
                },
                horizontalPadding = 18,
                verticalPadding = 8
            )
        }
    }
}