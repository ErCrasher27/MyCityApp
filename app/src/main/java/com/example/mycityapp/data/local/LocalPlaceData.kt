package com.example.mycityapp.data.local

import com.example.mycityapp.R
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.data.model.Place
import com.example.mycityapp.data.model.Rate

object LocalPlaceData {
    val places = listOf<Place>(
        Place(
            nameCategory = CategoryName.Activities,
            name = R.string.place_activities_name,
            descriptionPlace = R.string.place_activities_description,
            photoPlace = R.drawable.reggia_caserta,
            locationPlace = R.string.place_activities_location,
            ratingPlace = Rate.STAR_3,
        ),
        Place(
            nameCategory = CategoryName.Bar,
            name = R.string.place_bar_name,
            descriptionPlace = R.string.place_bar_description,
            photoPlace = R.drawable.reggia_caserta,
            locationPlace = R.string.place_bar_location,
            ratingPlace = Rate.STAR_5,
        ),
        Place(
            nameCategory = CategoryName.Bar,
            name = R.string.place_bar_name,
            descriptionPlace = R.string.place_bar_description,
            photoPlace = R.drawable.reggia_caserta,
            locationPlace = R.string.place_bar_location,
            ratingPlace = Rate.STAR_3,
        ),
        Place(
            nameCategory = CategoryName.Bar,
            name = R.string.place_bar_name,
            descriptionPlace = R.string.place_bar_description,
            photoPlace = R.drawable.reggia_caserta,
            locationPlace = R.string.place_bar_location,
            ratingPlace = Rate.STAR_2,
        ),
        Place(
            nameCategory = CategoryName.Bar,
            name = R.string.place_bar_name,
            descriptionPlace = R.string.place_bar_description,
            photoPlace = R.drawable.reggia_caserta,
            locationPlace = R.string.place_bar_location,
            ratingPlace = Rate.STAR_3,
        ),
        Place(
            nameCategory = CategoryName.Bar,
            name = R.string.place_bar_name,
            descriptionPlace = R.string.place_bar_description,
            photoPlace = R.drawable.reggia_caserta,
            locationPlace = R.string.place_bar_location,
            ratingPlace = Rate.STAR_2,
        ),
        Place(
            nameCategory = CategoryName.Bar,
            name = R.string.place_bar_name,
            descriptionPlace = R.string.place_bar_description,
            photoPlace = R.drawable.reggia_caserta,
            locationPlace = R.string.place_bar_location,
            ratingPlace = Rate.STAR_4,
        ),
        Place(
            nameCategory = CategoryName.Bar,
            name = R.string.place_bar_name,
            descriptionPlace = R.string.place_bar_description,
            photoPlace = R.drawable.reggia_caserta,
            locationPlace = R.string.place_bar_location,
            ratingPlace = Rate.STAR_1,
        )
    )
}