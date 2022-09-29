package com.example.mycityapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.mycityapp.data.local.LocalCategoryData
import com.example.mycityapp.data.local.LocalPlaceData
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
fun CategoriesHorizontalListsWithHeader(
    modifier: Modifier = Modifier,
    title: String,
    onCardClick: (CategoryName) -> Unit
) {
    Column {
        HeaderListCard(title = title)
        HorizontalPager(
            count = LocalCategoryData.categories.size,
            modifier,
            contentPadding = PaddingValues(horizontal = 60.dp)
        ) { page ->
            Card(
                onClick = { onCardClick(LocalCategoryData.categories[page].nameCategory) },
                Modifier
                    .width(600.dp)
                    .height(250.dp)
                    .testTag(LocalCategoryData.categories[page].nameCategory.name)
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
                    containerColor = Color(LocalCategoryData.categories[page].backgroundColor)
                ),
                shape = MaterialTheme.shapes.large
            ) {
                CategoryCard(
                    category = LocalCategoryData.categories[page],
                )
            }
        }
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BestPlacesHorizontalListWithHeader(
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType,
    title: String,
    viewModel: MyCityViewModel,
    isInHomePage: Boolean
) {
    Column {
        val bestPlacesWithFourOrMoreStars =
            LocalPlaceData.places.filter { place -> place.ratingPlace == Rate.STAR4 || place.ratingPlace == Rate.STAR5 }
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
                    horizontalPadding = 0,
                    verticalPadding = 0,
                    navigationType = navigationType,
                    place = bestPlacesWithFourOrMoreStars[page],
                    isInHomePage = isInHomePage,
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
                )
            }
        }
    }
}