package com.example.mycityapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycityapp.data.model.Category

@Composable
fun CategoryCard(
    category: Category,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 22.dp, end = 22.dp)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Box(modifier = modifier.height(200.dp)) {
                Image(
                    painter = painterResource(category.backgroundCategory),
                    contentDescription = category.nameCategory.name,
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(start = 8.dp, end = 8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Column {
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
        }
    }
}

