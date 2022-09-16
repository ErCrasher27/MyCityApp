package com.example.mycityapp.data.local

import com.example.mycityapp.R
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.data.model.Rate

object LocalPlaceData {
    val places = listOf<Place>(
        Place(
            nameCategory = CategoryName.Activities,
            name = R.string.place_activities_name1,
            descriptionPlace = R.string.place_activities_description1,
            photoPlace = R.drawable.reggia_caserta,
            locationPlace = R.string.place_activities_location1,
            phonePlace = R.string.place_activities_phone1,
            ratingPlace = Rate.STAR1
        ),
        Place(
            nameCategory = CategoryName.Bar,
            name = R.string.place_bar_name1,
            descriptionPlace = R.string.place_bar_description1,
            photoPlace = R.drawable.reggia_caserta,
            locationPlace = R.string.place_bar_location1,
            ratingPlace = Rate.STAR5
        ),
        Place(
            nameCategory = CategoryName.Activities,
            name = R.string.place_bar_name1,
            descriptionPlace = R.string.place_bar_description1,
            photoPlace = R.drawable.reggia_caserta,
            locationPlace = R.string.place_bar_location1,
            ratingPlace = Rate.STAR5
        )
    )
}