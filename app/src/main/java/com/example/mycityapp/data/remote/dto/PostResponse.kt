package com.example.mycityapp.data.remote.dto

import android.location.Location
import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val location: LocationWeather?,
    val current: CurrentWeather?
)
@Serializable
data class CurrentWeather(
    val condition: WeatherCondition?,
    val temp_c: Float,
    val is_day: Int,
    val wind_kph: Float,
    val last_updated: String,
)
@Serializable
data class WeatherCondition(
    val icon: String,
    val text: String,
)
@Serializable
data class LocationWeather(
    val name: String,
    val region: String,
    val lat: String,
    val lon: String,
)

