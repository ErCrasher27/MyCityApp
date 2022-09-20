package com.example.mycityapp.data.local

import com.example.mycityapp.R
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.data.model.Rate

object LocalPlaceData {
    val places = listOf<Place>(
        Place(
            nameCategory = CategoryName.activities,
            name = R.string.place_activities_name1,
            descriptionPlace = R.string.place_activities_description1,
            photoPlace = R.drawable.place_activities_image_1,
            locationPlace = R.string.place_activities_location1,
            phonePlace = R.string.place_activities_phone1,
            ratingPlace = Rate.star4
        ),
        Place(
            nameCategory = CategoryName.panoramas,
            name = R.string.place_panoramas_name1,
            descriptionPlace = R.string.place_panoramas_description1,
            photoPlace = R.drawable.place_panoramas_image_1,
            locationPlace = R.string.place_panoramas_location1,
            ratingPlace = Rate.star5
        ),
        Place(
            nameCategory = CategoryName.restaurants,
            name = R.string.place_restaurants_name1,
            descriptionPlace = R.string.place_restaurants_description1,
            photoPlace = R.drawable.place_restaurant_image_1,
            locationPlace = R.string.place_restaurants_location1,
            ratingPlace = Rate.star4
        ),
        Place(
            nameCategory = CategoryName.bar,
            name = R.string.place_bar_name1,
            descriptionPlace = R.string.place_bar_description1,
            photoPlace = R.drawable.place_bar_image_1,
            locationPlace = R.string.place_bar_location1,
            ratingPlace = Rate.star3
        )
    )
}