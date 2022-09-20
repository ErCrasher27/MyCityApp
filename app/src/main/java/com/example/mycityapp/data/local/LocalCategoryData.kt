package com.example.mycityapp.data.local

import com.example.mycityapp.R
import com.example.mycityapp.data.model.Category
import com.example.mycityapp.data.model.CategoryName

object LocalCategoryData {
    val categories = listOf<Category>(
        Category(
            nameCategory = CategoryName.activities,
            descriptionCategory = R.string.category_activities_description,
            backgroundCategory = R.drawable.background_activities_category
        ),
        Category(
            nameCategory = CategoryName.panoramas,
            descriptionCategory = R.string.category_panoramas_description,
            backgroundCategory = R.drawable.background_panoramas_category
        ),
        Category(
            nameCategory = CategoryName.restaurants,
            descriptionCategory = R.string.category_restaurations_description,
            backgroundCategory = R.drawable.background_restaurants_category
        ),
        Category(
            nameCategory = CategoryName.bar,
            descriptionCategory = R.string.category_bar_description,
            backgroundCategory = R.drawable.background_bar_category
        )
    )
}