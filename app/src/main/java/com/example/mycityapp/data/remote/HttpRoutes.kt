package com.example.mycityapp.data.remote

import android.location.Location
import com.example.mycityapp.BuildConfig

class HttpRoutesString {
    companion object HttpRoutes {
        //Base Url to make weather api requests
        private val BASE_WEATHER_URL = "https://api.weatherapi.com/v1"

        //Specify that the json from the request we need is current
        val CURRENT_WEATHER_JSON = "$BASE_WEATHER_URL/current.json"

        //Will return the string with the actual location of the user to make the API request
        fun returnCurrentWeatherString(location: Location?): String {
            val apiKey = BuildConfig.WEATHER_API_KEY
            return "$CURRENT_WEATHER_JSON?key=${apiKey}&q=${location?.latitude},${location?.longitude}&aqi=no&lang=it"
        }
    }
}