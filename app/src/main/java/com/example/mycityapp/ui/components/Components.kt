package com.example.mycityapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mycityapp.R
import com.example.mycityapp.data.model.Category
import com.example.mycityapp.data.model.Place

@Composable
fun HeaderListCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            text = "Categories",
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
    Column(
        modifier = modifier
            .padding(30.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            color = MaterialTheme.colorScheme.onSecondary,
            text = category.nameCategory.name,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            color = MaterialTheme.colorScheme.onSecondary,
            text = stringResource(category.descriptionCategory),
            style = MaterialTheme.typography.labelMedium,
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceCard(
    place: Place,
    onPlaceClick: (Place) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier
            .padding(horizontal = 18.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp)),
        onClick = { onPlaceClick(place) },
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 13.dp)
        ) {
            TitleAndDescriptionPlace(place = place)
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = modifier
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(vertical = 8.dp)
            ) {
                LocationPlace(
                    location = place.locationPlace,
                    horizontalArrangement = Arrangement.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                StarsPlace(star = place.ratingPlace, horizontalArrangement = Arrangement.Start)
            }
        }
    }

}


@Composable
fun DetailsPlace(place: Place, onClose: () -> Unit) {
    Dialog(
        onDismissRequest = onClose,
        content = { DetailsPlaceCard(place = place, onClose = onClose) }
    )
}

@Composable
fun DetailsPlaceCard(place: Place, onClose: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 4.dp)
    ) {
        IconButton(onClick = onClose) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.close_details_place)
            )
        }
        Column(
            modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 13.dp)
            ) {
                TitleAndDescriptionPlace(place = place)
                Spacer(modifier = Modifier.height(4.dp))
                StarsPlace(star = place.ratingPlace, horizontalArrangement = Arrangement.Center)
            }
            ImagePlace(imagePlace = place.photoPlace, namePlace = place.name)
            Spacer(modifier = Modifier.height(4.dp))
            LocationPlace(
                location = place.locationPlace,
                horizontalArrangement = Arrangement.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (place.phonePlace != null) {
                PhonePlace(numberPhone = place.phonePlace)
            }
        }
    }
}


