package com.example.mycityapp.data.remote.dto

import android.location.Location
import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val location: locationWeather?,
    val current: currentWeather?
)
@Serializable
data class currentWeather(
    val temp_c: Float,
    val is_day: Int,
    val wind_kph: Float,
    val last_updated: String,
)
@Serializable
data class locationWeather(
    val name: String,
    val region: String,
    val lat: String,
    val lon: String,
)

