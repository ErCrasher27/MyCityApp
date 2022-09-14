package com.example.mycityapp.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mycityapp.data.local.LocalCategoryData.categories
import com.example.mycityapp.ui.components.CategoryCard

@Composable
fun ContentApp(modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(categories) { category ->
            CategoryCard(category)
        }
    }
}