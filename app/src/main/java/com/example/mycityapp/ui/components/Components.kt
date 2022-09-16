package com.example.mycityapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.window.Dialog
import com.example.mycityapp.data.model.Category
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Place
import com.google.accompanist.pager.*
import com.google.accompanist.pager.calculateCurrentOffsetForPage

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
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
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp)),
        onClick = { onPlaceClick(place) }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            Text(
                text = stringResource(place.name),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = modifier.height(4.dp))
            Text(
                text = stringResource(place.descriptionPlace),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@Composable
fun DetailsPlace(place: Place, onClose: () -> Unit) {
    Dialog(
        onDismissRequest = onClose,
        content = { DetailsPlaceCard(place = place, onClose = onClose) })
}

@Composable
fun DetailsPlaceCard(place: Place, onClose: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier
            .fillMaxSize()
    ) {
        Column {
            IconButton(onClick = onClose) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .padding(16.dp)
                    .fillMaxWidth(
                    )
            ) {
                Text(
                    text = stringResource(id = place.name),
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = place.ratingPlace.name,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            ) {
                Image(
                    painter = painterResource(id = place.photoPlace),
                    contentDescription = stringResource(id = place.name),
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = stringResource(id = place.descriptionPlace),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(id = place.locationPlace),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


