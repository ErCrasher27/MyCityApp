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
        )
    )
}