package com.example.mycityapp.data.model

import android.graphics.Color
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemContent(
    val icon: ImageVector,
    val text: CategoryName,
)

data class Category(
    val nameCategory: CategoryName,
    @StringRes val descriptionCategory: Int,
    @DrawableRes val backgroundCategory: Int,
    val backgroundColor: Int,
    val icon: ImageVector
)

data class Place(
    val category: Category,
    @StringRes val name: Int,
    @StringRes val descriptionPlace: Int,
    @DrawableRes val photoPlace: Int,
    @StringRes val locationPlace: Int,
    @StringRes val phonePlace: Int? = null,
    val ratingPlace: Rate,
)







