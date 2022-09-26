package com.example.mycityapp.data.local

import com.example.mycityapp.R
import com.example.mycityapp.data.local.LocalCategoryData.categories
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.data.model.Rate
import com.google.android.gms.maps.model.LatLng

object LocalPlaceData {
    val places = listOf<Place>(
        Place(
            category = categories[0],
            name = R.string.place_activities_name1,
            descriptionPlace = R.string.place_activities_description1,
            photoPlace = listOf
                (
                R.drawable.place_activities_image_1,
                R.drawable.place_activities_image_1,
                R.drawable.place_activities_image_1,
                R.drawable.place_activities_image_1
            ),
            locationPlace = R.string.place_activities_location1,
            latLng = LatLng(41.07592251854585, 14.327369549252298),
            phonePlace = "327 903 0132",
            ratingPlace = Rate.STAR4
        ),
        Place(
            category = categories[1],
            name = R.string.place_panoramas_name1,
            descriptionPlace = R.string.place_panoramas_description1,
            photoPlace = listOf
                (R.drawable.place_panoramas_image_1),
            locationPlace = R.string.place_panoramas_location1,
            latLng = LatLng(41.07592251854585, 14.327369549252298),
            ratingPlace = Rate.STAR5
        ),
        Place(
            category = categories[2],
            name = R.string.place_restaurants_name1,
            descriptionPlace = R.string.place_restaurants_description1,
            photoPlace = listOf
                (R.drawable.place_restaurant_image_1),
            locationPlace = R.string.place_restaurants_location1,
            latLng = LatLng(41.07592251854585, 14.327369549252298),
            ratingPlace = Rate.STAR4
        ),
        Place(
            category = categories[3],
            name = R.string.place_bar_name1,
            descriptionPlace = R.string.place_bar_description1,
            photoPlace = listOf
                (R.drawable.place_bar_image_1),
            locationPlace = R.string.place_bar_location1,
            latLng = LatLng(41.07592251854585, 14.327369549252298),
            ratingPlace = Rate.STAR3
        )
    )
}