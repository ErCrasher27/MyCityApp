package com.example.mycityapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycityapp.data.model.Category
import com.example.mycityapp.data.model.Place

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
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
}

@Composable
fun PlaceCard(
    place: Place,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 4.dp)
            .clip(RoundedCornerShape(8.dp)),
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


