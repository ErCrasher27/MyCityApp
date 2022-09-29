package com.example.mycityapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.ui.utils.MyCityNavigationType
import com.google.android.gms.maps.model.LatLng

@Composable
fun PlaceCard(
    modifier: Modifier = Modifier,
    navigationType: MyCityNavigationType,
    horizontalPadding: Int,
    verticalPadding: Int,
    place: Place,
    isInHomePage: Boolean,
    onPlaceClick: (Place) -> Unit,
    onClickToGo: (LatLng) -> Unit,
    loadDistance: (LatLng) -> String?
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
                .padding(vertical = 12.dp)
        ) {
            if (isInHomePage) {
                Image(
                    modifier = modifier
                        .fillMaxWidth(),
                    imageVector = place.category.icon,
                    contentDescription = null
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
                DistanceToPlace(placePosition = place.latLng, loadDistance = loadDistance)
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