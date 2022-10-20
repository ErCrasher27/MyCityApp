package com.example.mycityapp.ui.components

import android.Manifest
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mycityapp.R
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.ui.RequestPermissions
import com.example.mycityapp.ui.utils.MyCityNavigationType
import com.google.android.gms.maps.model.LatLng


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
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType = MyCityNavigationType.BOTTOM_NAVIGATION,
    place: Place,
    onClose: () -> Unit,
    onClickToCall: (String) -> Unit,
    onClickToGo: (LatLng) -> Unit,
) {
    val buttonCloseTestAndContentDescription = stringResource(id = R.string.close_details_place)

    val context = LocalContext.current
    RequestPermissions(
        context = context,
        namePermission = "Phone",
        permission = Manifest.permission.CALL_PHONE
    )
    RequestPermissions(
        context = context,
        namePermission = "Location",
        permission = Manifest.permission.ACCESS_FINE_LOCATION
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
                    contentDescription = buttonCloseTestAndContentDescription,
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
                place = place,
                enablePhone = enablePhone,
                onClickToCall = onClickToCall
            )
            ClickToGo(
                navigationType = MyCityNavigationType.PERMANENT_NAVIGATION_DRAWER,
                latLng = place.latLng,
                onClickToGo = onClickToGo
            )
        }
    }
}


