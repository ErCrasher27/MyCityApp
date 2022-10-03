package com.example.mycityapp.data.remote

import android.location.Location
import com.example.mycityapp.data.remote.dto.PostRequest

class HttpRoutesString{
    companion object HttpRoutes {
        private val BASE_WEATHER_URL = "https://api.weatherapi.com/v1"
        val CURRENT_WEATHER_JSON = "$BASE_WEATHER_URL/current.json"
        fun returnCurrentWeatherString(location: Location?): String {
            return "$CURRENT_WEATHER_JSON?key=37c83c2007634a058af124740222709&q=${location?.latitude},${location?.longitude}&aqi=no"
        }
    }
}