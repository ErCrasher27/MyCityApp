package com.example.mycityapp.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.mycityapp.data.local.LocalCategoryData.categories
import com.example.mycityapp.data.local.LocalPlaceData
import com.example.mycityapp.data.model.CategoryName
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
    if (currentTab.name == CategoryName.homepage.name) {
        CategoriesHorizontalListsWithHeader(onCardClick = onCardClick, title = "categories")
    } else {
        PlacesLists(currentTab = currentTab, viewModel = viewModel)
    }
}

/*
@Composable
fun ListAndDetailsCard(
    currentTab: CategoryName,
    onCardClick: (CategoryName) -> Unit,
    viewModel: MyCityViewModel,
    modifier: Modifier = Modifier
) {
    if (currentTab.name == CategoryName.HomePage.name) {
        LazyColumn(modifier) {
            items(categories) { category ->
                CategoryCard(
                    category = category,
                )
            }
        }
    } else {
        LazyColumn(modifier) {
            items(LocalPlaceData.places.filter { it.nameCategory.name == currentTab.name }) { place ->
                PlaceCard(
                    place = place,
                    onPlaceClick = {
                        viewModel.updateCurrentDetails(it)
                    }
                )
            }
        }
    }
}*/

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
        contentPadding = PaddingValues(horizontal = 70.dp)
    ) { page ->
        Card(
            onClick = { onCardClick(categories[page].nameCategory) },
            Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
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
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            CategoryCard(
                category = categories[page],
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
        items(LocalPlaceData.places.filter { it.nameCategory.name == currentTab.name }) { place ->
            PlaceCard(
                place = place,
                onPlaceClick = {
                    viewModel.updateCurrentDetails(it)
                }
            )
        }
    }
}