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
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlin.math.absoluteValue

@Composable
fun TitleAndDescriptionPlace(place: Place, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(place.name),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "\"" + stringResource(place.descriptionPlace) + "\"",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun LocationPlace(
    @StringRes location: Int, horizontalArrangement: Arrangement.Horizontal,
    modifier: Modifier = Modifier
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
            tint = MaterialTheme.colorScheme.tertiary
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = stringResource(location),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Composable
fun PhonePlace(
    numberPhone: String,
    modifier: Modifier = Modifier
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
            tint = MaterialTheme.colorScheme.tertiary
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(
            text = numberPhone,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}

@Composable
fun StarsPlace(
    star: Rate,
    horizontalArrangement: Arrangement.Horizontal,
    modifier: Modifier = Modifier
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
    @DrawableRes imagePlace: Int,
    @StringRes namePlace: Int,
    modifier: Modifier = Modifier
) {
    //start
    val pagerState = rememberPagerState()
    val count = 4 //to change

    TabRow(
        // Our selected tab is our current page
        selectedTabIndex = pagerState.currentPage,
        // Override the indicator, using the provided pagerTabIndicatorOffset modifier
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        // Add tabs for all of our pages
        count.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) },
                selected = pagerState.currentPage == index,
                onClick = { /* TODO */ },
            )
        }
    }

    HorizontalPager(
        count = count,
        modifier,
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
                },
            shape = MaterialTheme.shapes.large
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = imagePlace),
                    contentDescription = stringResource(namePlace),
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(MaterialTheme.shapes.large)
                )
            }
        }
    }


    //end


}

@Composable
fun TextWithShadow(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier
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
    onClick: (Place) -> Unit,
    navigationType: MyCityNavigationType,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        modifier = modifier.widthIn(100.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Navigation,
            contentDescription = stringResource(id = R.string.navigate_to_place),
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        if (navigationType != MyCityNavigationType.BOTTOM_NAVIGATION) {
            Spacer(Modifier.size(ButtonDefaults.IconSize))
            Text(text = stringResource(id = R.string.navigate_to_place))
        }
    }
}

@Composable
fun ClickToCall(
    onClickToCall: (String) -> Unit,
    place: Place,
    enablePhone: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        enabled = enablePhone,
        onClick = { place.phonePlace?.let { onClickToCall(it) } },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
        modifier = modifier.widthIn(100.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Call,
            contentDescription = stringResource(id = R.string.call_place),
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(Modifier.size(ButtonDefaults.IconSize))
        Text(text = stringResource(id = R.string.call_place))
    }
}

@Composable
fun ClickForMore(
    onClick: (Place) -> Unit,
    place: Place,
    navigationType: MyCityNavigationType,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClick(place) },
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary),
        modifier = modifier.widthIn(100.dp)
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
fun MapPlace(place: Place, modifier: Modifier = Modifier) {
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