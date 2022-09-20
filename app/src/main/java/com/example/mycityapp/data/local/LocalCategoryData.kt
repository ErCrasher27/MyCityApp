package com.example.mycityapp.data.local

import android.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalActivity
import androidx.compose.material.icons.filled.Panorama
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.WineBar
import com.example.mycityapp.R
import com.example.mycityapp.data.model.Category
import com.example.mycityapp.data.model.CategoryName

object LocalCategoryData {
    val categories = listOf<Category>(
        Category(
            nameCategory = CategoryName.Activities,
            descriptionCategory = R.string.category_activities_description,
            backgroundCategory = R.drawable.background_activities_category,
            backgroundColor = Color.GREEN,
            icon = Icons.Default.LocalActivity
        ),
        Category(
            nameCategory = CategoryName.Panoramas,
            descriptionCategory = R.string.category_panoramas_description,
            backgroundCategory = R.drawable.background_panoramas_category,
            backgroundColor = Color.BLUE,
            icon = Icons.Default.Panorama
        ),
        Category(
            nameCategory = CategoryName.Restaurants,
            descriptionCategory = R.string.category_restaurations_description,
            backgroundCategory = R.drawable.background_restaurants_category,
            backgroundColor = Color.RED,
            icon = Icons.Default.Restaurant
        ),
        Category(
            nameCategory = CategoryName.Bar,
            descriptionCategory = R.string.category_bar_description,
            backgroundCategory = R.drawable.background_bar_category,
            backgroundColor = Color.YELLOW,
            icon = Icons.Default.WineBar

        )
    )
}