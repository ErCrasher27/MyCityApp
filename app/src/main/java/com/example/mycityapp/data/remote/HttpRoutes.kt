package com.example.mycityapp.data.remote

import com.example.mycityapp.data.remote.dto.PostRequest

object HttpRoutes {
    private const val BASE_WEATHER_URL = "https://api.weatherapi.com/v1"
    const val CURRENT_WEATHER_JSON = "$BASE_WEATHER_URL/current.json"
    const val CURRENT_WEATHER_JSON_REQUEST = "$CURRENT_WEATHER_JSON?key=37c83c2007634a058af124740222709&q=Caserta&aqi=no"
}