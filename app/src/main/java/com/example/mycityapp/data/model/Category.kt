package com.example.mycityapp.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Category(
    val nameCategory: CategoryName,
    @StringRes val descriptionCategory: Int,
    @DrawableRes val backgroundCategory: Int
)
