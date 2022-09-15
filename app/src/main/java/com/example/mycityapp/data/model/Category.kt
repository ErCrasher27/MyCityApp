package com.example.mycityapp.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    val nameCategory: CategoryName,
    @StringRes val descriptionCategory: Int,
    @DrawableRes val backgroundCategory: Int
)

data class Place(
    val nameCategory: CategoryName,
    @StringRes val name: Int,
    @StringRes val descriptionPlace: Int,
    @DrawableRes val photoPlace: Int,
    @StringRes val locationPlace: Int,
    val ratingPlace: Rate,
)






