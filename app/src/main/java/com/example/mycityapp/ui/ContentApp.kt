package com.example.mycityapp.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mycityapp.data.local.LocalCategoryData
import com.example.mycityapp.data.local.LocalPlaceData
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.ui.components.CategoryCard
import com.example.mycityapp.ui.components.PlaceCard

@Composable
fun OnlyListCategoryCards(
    currentTab: CategoryName,
    modifier: Modifier = Modifier
) {
    if (currentTab.name == CategoryName.HomePage.name) {
        LazyColumn(modifier) {
            items(LocalCategoryData.categories) { category ->
                CategoryCard(category)
            }
        }
    } else {
        LazyColumn(modifier) {
            items(LocalPlaceData.places.filter { it.nameCategory.name == currentTab.name }) { place ->
                PlaceCard(place)
            }
        }
    }
}

@Composable
fun ListAndDetailsCategoryCard(
    currentTab: CategoryName,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(LocalCategoryData.categories) { category ->
            CategoryCard(category)
        }
    }
}