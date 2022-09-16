package com.example.mycityapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.mycityapp.R
import com.example.mycityapp.data.model.Category
import com.example.mycityapp.data.model.Place

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(30.dp)
    ) {
        Text(
            text = category.nameCategory.name,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = modifier.height(4.dp))
        Text(
            text = stringResource(category.descriptionCategory),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary,
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
            LocationPlace(
                location = place.locationPlace,
                horizontalArrangement = Arrangement.Start
            )
            Spacer(modifier = Modifier.height(4.dp))
            StarsPlace(star = place.ratingPlace, horizontalArrangement = Arrangement.Start)
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
        modifier
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


