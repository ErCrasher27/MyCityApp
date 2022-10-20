package com.example.mycityapp.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.rememberAsyncImagePainter
import com.example.mycityapp.data.local.LocalCategoryData
import com.example.mycityapp.data.local.LocalPlaceData
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Rate
import com.example.mycityapp.data.remote.dto.PostResponse
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

@SuppressLint("StateFlowValueCalledInComposition")
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

@Composable
fun WeatherWithHeader(
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType,
    title: String,
    viewModel: MyCityViewModel,
    isInHomePage: Boolean
) {
    if (viewModel.uiState.value.weather != null) {
        Column {
            HeaderListCard(title = title)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(start = 20.dp, end = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inverseSurface,
                ),
                shape = MaterialTheme.shapes.large
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(4.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(70.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter("https:" + viewModel.uiState.value.weather!!.current?.condition?.icon),
                            contentDescription = "Image of weather",
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Column(modifier = Modifier.padding(start = 8.dp)) {
                        viewModel.uiState.value.weather!!.current?.condition?.text?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        viewModel.uiState.value.weather!!.location?.name?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }

                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(70.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = viewModel.uiState.value.weather!!.current?.temp_c?.toInt()
                                .toString() + "°C", style = MaterialTheme.typography.titleLarge
                        )
                    }

                }
            }
        }
        ConsideringTheWeatherHorizontalList(
            navigationType = navigationType,
            viewModel = viewModel,
            isInHomePage = isInHomePage,
        )
    }
    if (viewModel.uiState.value.currentLocation != null) {
        produceState<PostResponse?>(initialValue = null, producer = {
            viewModel.callWeatherApi()
            value = viewModel.uiState.value.weather
        })
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ConsideringTheWeatherHorizontalList(
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType,
    viewModel: MyCityViewModel,
    isInHomePage: Boolean
) {
    Column {
        val isDay =
            when (viewModel.uiState.value.weather?.current?.is_day) {
                0 -> false
                1 -> true
                else -> {
                    true
                }
            }
        val bestPlacesWithFourOrMoreStars =
            LocalPlaceData.places.filter { place -> place.dayVisitable == isDay }
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