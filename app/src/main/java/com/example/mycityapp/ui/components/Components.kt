package com.example.mycityapp.ui.components


import android.Manifest
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mycityapp.R
import com.example.mycityapp.data.model.Category
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.ui.RequestPermissions
import com.example.mycityapp.ui.utils.MyCityNavigationType
import com.google.android.gms.maps.model.LatLng

@Composable
fun HeaderListCard(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp, bottom = 10.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = category.backgroundCategory),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            TextWithShadow(
                text = category.nameCategory.name,
                modifier = modifier.padding(top = 10.dp),
                style = MaterialTheme.typography.titleLarge
            )
            TextWithShadow(
                text = stringResource(id = category.descriptionCategory),
                modifier = modifier.padding(top = 0.dp),
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}

@Composable
fun PlaceCard(
    place: Place,
    onPlaceClick: (Place) -> Unit,
    onClickToGo: (LatLng) -> Unit,
    horizontalPadding: Int,
    verticalPadding: Int,
    isInHomePage: Boolean,
    navigationType: MyCityNavigationType,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .padding(horizontal = horizontalPadding.dp, vertical = verticalPadding.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 13.dp)
        ) {
            if (isInHomePage) {
                Image(
                    modifier = modifier
                        .fillMaxWidth(),
                    imageVector = place.category.icon,
                    contentDescription = place.category.nameCategory.name
                )
            }
            TitleAndDescriptionPlace(place = place)
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = modifier
                    .padding(horizontal = 8.dp)
                    .clip(MaterialTheme.shapes.large)
                    .padding(vertical = 8.dp)
            ) {
                LocationPlace(
                    location = place.locationPlace,
                    horizontalArrangement = Arrangement.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                StarsPlace(star = place.ratingPlace, horizontalArrangement = Arrangement.Start)
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ClickToGo(
                    onClickToGo = onClickToGo,
                    latLng = place.latLng,
                    navigationType = navigationType,
                )
                ClickForMore(
                    onClick = onPlaceClick,
                    place = place,
                    navigationType = navigationType
                )
            }
        }
    }
}


@Composable
fun DetailsPlace(
    place: Place,
    onClose: () -> Unit,
    onClickToCall: (String) -> Unit,
    onClickToGo: (LatLng) -> Unit
) {
    Dialog(
        onDismissRequest = onClose,
        content = {
            DetailsPlaceCard(
                place = place,
                onClose = onClose,
                onClickToCall = onClickToCall,
                onClickToGo = onClickToGo
            )
        }
    )
}

@Composable
fun DetailsPlaceCard(
    place: Place,
    onClose: () -> Unit,
    onClickToCall: (String) -> Unit,
    onClickToGo: (LatLng) -> Unit,
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType = MyCityNavigationType.BOTTOM_NAVIGATION
) {
    val context = LocalContext.current
    RequestPermissions(
        namePermission = "Phone",
        permission = Manifest.permission.CALL_PHONE,
        context = context
    )
    RequestPermissions(
        namePermission = "Location",
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        context = context
    )
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = MaterialTheme.shapes.large,
        modifier = modifier
            .fillMaxSize()
    ) {
        if (navigationType != MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER) {
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(id = R.string.close_details_place),
                )
            }
        }
        Column(
            modifier
                .weight(10f)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            TitleAndDescriptionPlace(place = place)
            StarsPlace(
                star = place.ratingPlace,
                horizontalArrangement = Arrangement.Center
            )
            ImagePlace(imagePlace = place.photoPlace, namePlace = place.name)
            LocationPlace(
                location = place.locationPlace,
                horizontalArrangement = Arrangement.Start
            )
            if (place.phonePlace != null) {
                PhonePlace(numberPhone = place.phonePlace)
            }
            MapPlace(place = place)
        }
        Row(
            modifier = modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(top = 2.dp, bottom = 2.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            val enablePhone = place.phonePlace != null
            ClickToCall(
                onClickToCall = onClickToCall,
                place = place,
                enablePhone = enablePhone,
            )
            ClickToGo(
                onClickToGo = onClickToGo,
                latLng = place.latLng,
                navigationType = MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER,
            )
        }
    }
}






