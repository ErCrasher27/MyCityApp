package com.example.mycityapp.data.remote.dto

import com.example.mycityapp.BuildConfig

val apiKey = BuildConfig.WEATHER_API_KEY

data class PostRequest(
    val Key: String = apiKey,
    val q: String = "",
    val aqi: String = "no"
)
