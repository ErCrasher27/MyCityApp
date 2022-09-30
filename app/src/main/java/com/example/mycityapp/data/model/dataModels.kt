package com.example.mycityapp.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.android.gms.maps.model.LatLng

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
    @DrawableRes val photoPlace: List<Int>,
    @StringRes val locationPlace: Int,
    val phonePlace: String? = null,
    val latLng: LatLng,
    val ratingPlace: Rate,
    val dayVisitable: Boolean,
    val nightVisitable: Boolean
)








