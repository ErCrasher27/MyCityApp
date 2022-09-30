package com.example.mycityapp.ui.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.example.mycityapp.R
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.data.model.Rate
import com.example.mycityapp.ui.utils.MyCityNavigationType
import com.google.accompanist.pager.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlin.math.absoluteValue

@Composable
fun TitleAndDescriptionPlace(modifier: Modifier = Modifier, place: Place) {
    val tagPlaceDescription = stringResource(id = place.descriptionPlace)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .testTag(tagPlaceDescription),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(place.name),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "\"" + stringResource(place.descriptionPlace) + "\"",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun LocationPlace(
    modifier: Modifier = Modifier,
    @StringRes location: Int,
    horizontalArrangement: Arrangement.Horizontal
) {
    Row(
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = stringResource(location),
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(location),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun PhonePlace(
    modifier: Modifier = Modifier,
    numberPhone: String
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = numberPhone,
            tint = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = numberPhone,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun StarsPlace(
    modifier: Modifier = Modifier,
    star: Rate,
    horizontalArrangement: Arrangement.Horizontal
) {
    Row(
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        val repeater: Int = when (star) {
            Rate.STAR1 -> 1
            Rate.STAR2 -> 2
            Rate.STAR3 -> 3
            Rate.STAR4 -> 4
            Rate.STAR5 -> 5
        }
        repeat(repeater) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = star.name,
                tint = Color(0xFFC7A005)

            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ImagePlace(
    modifier: Modifier = Modifier,
    @DrawableRes imagePlace: List<Int>,
    @StringRes namePlace: Int
) {
    val pagerState = rememberPagerState()

    HorizontalPager(
        count = imagePlace.size, modifier,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 0.dp)
    ) { page ->
        Card(
            Modifier
                .width(600.dp)
                .height(200.dp)
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
                }) {
            Box(
                modifier = modifier
                    .fillMaxSize()
            ) {
                Image(
                    modifier = modifier
                        .fillMaxSize(),
                    painter = painterResource(id = imagePlace[page]),
                    contentDescription = stringResource(namePlace),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPagerIndicator(
            modifier = Modifier
                .padding(top = 16.dp, bottom = 8.dp),
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun TextWithShadow(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle
) {
    Box {
        Text(
            text = text,
            modifier = modifier
                .offset(
                    x = 2.dp,
                    y = 2.dp
                )
                .alpha(0.75f),
            style = style,
            color = DarkGray
        )
        Text(
            text = text,
            modifier = modifier,
            style = style,
            color = White
        )
    }
}

@Composable
fun ClickToGo(
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType,
    latLng: LatLng,
    onClickToGo: (LatLng) -> Unit,
) {
    Button(
        modifier = modifier.widthIn(100.dp),
        onClick = { onClickToGo(latLng) },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Icon(
            modifier = Modifier.size(ButtonDefaults.IconSize),
            imageVector = Icons.Default.Navigation,
            contentDescription = stringResource(id = R.string.navigate_to_place),
        )
        if (navigationType != MyCityNavigationType.BOTTOM_NAVIGATION) {
            Spacer(Modifier.size(ButtonDefaults.IconSize))
            Text(text = stringResource(id = R.string.navigate_to_place))
        }
    }
}

@Composable
fun ClickToCall(
    modifier: Modifier = Modifier,
    place: Place,
    enablePhone: Boolean,
    onClickToCall: (String) -> Unit,
) {
    Button(
        modifier = modifier.widthIn(100.dp),
        enabled = enablePhone,
        onClick = { place.phonePlace?.let { onClickToCall(it) } },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
    ) {
        Icon(
            modifier = Modifier.size(ButtonDefaults.IconSize),
            imageVector = Icons.Default.Call,
            contentDescription = stringResource(id = R.string.call_place),
        )
        Spacer(Modifier.size(ButtonDefaults.IconSize))
        Text(text = stringResource(id = R.string.call_place))
    }
}

@Composable
fun ClickForMore(
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType,
    place: Place,
    onClick: (Place) -> Unit,
) {
    val placeTestTag = stringResource(id = place.locationPlace)
    val buttonTestTagMediumAndDrawer = stringResource(id = R.string.more_details_place)
    Button(
        onClick = { onClick(place) },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        modifier = modifier
            .widthIn(100.dp)
            .testTag(placeTestTag)

    ) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = stringResource(id = R.string.more_details_place),
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        if (navigationType != MyCityNavigationType.BOTTOM_NAVIGATION) {
            Spacer(Modifier.size(ButtonDefaults.IconSize))
            Text(text = stringResource(id = R.string.more_details_place))
        }
    }
}

@Composable
fun MapPlace(modifier: Modifier = Modifier, place: Place) {
    val locationMaps = place.latLng
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(locationMaps, 12f)
    }
    Column(
        modifier = modifier
            .height(340.dp)
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 4.dp, start = 24.dp, end = 24.dp)
            .clip(shape = MaterialTheme.shapes.large)
    ) {

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = MarkerState(position = locationMaps),
                title = stringResource(id = place.name),
                snippet = stringResource(id = place.locationPlace),
                tag = place.category.nameCategory.name
            )
        }
    }
}

@Composable
fun DistanceToPlace(
    modifier: Modifier = Modifier,
    placePosition: LatLng,
    loadDistance: (LatLng) -> String?
) {
    val distanceCalculated = loadDistance(placePosition)
    if (distanceCalculated != null) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationSearching,
                contentDescription = "$distanceCalculated km from you",
                tint = MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                text = "$distanceCalculated km from you",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

