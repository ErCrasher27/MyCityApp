package com.example.mycityapp.data.local

import com.example.mycityapp.R
import com.example.mycityapp.data.model.Category
import com.example.mycityapp.data.model.CategoryName

object LocalCategoryData {
    val categories = listOf<Category>(
        Category(
            nameCategory = CategoryName.Activities,
            descriptionCategory = R.string.category_activities_description,
            backgroundCategory = R.drawable.background_activities_category
        ),
        Category(
            nameCategory = CategoryName.Restaurants,
            descriptionCategory = R.string.category_restaurations_description,
            backgroundCategory = R.drawable.background_restaurants_category
        ),
        Category(
            nameCategory = CategoryName.Bar,
            descriptionCategory = R.string.category_bar_description,
            backgroundCategory = R.drawable.background_bar_category
        ),
        Category(
            nameCategory = CategoryName.Panoramas,
            descriptionCategory = R.string.category_panoramas_description,
            backgroundCategory = R.drawable.background_panoramas_category
        )
    )
}