package com.example.mycityapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mycityapp.data.model.Category

@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: Category
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            modifier = modifier.fillMaxSize(),
            painter = painterResource(id = category.backgroundCategory),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            TextWithShadow(
                modifier = modifier.padding(top = 10.dp),
                text = category.nameCategory.name,
                style = MaterialTheme.typography.titleLarge
            )
            TextWithShadow(
                modifier = modifier.padding(top = 0.dp),
                text = stringResource(id = category.descriptionCategory),
                style = MaterialTheme.typography.bodyMedium
            )
        }

    }

}
