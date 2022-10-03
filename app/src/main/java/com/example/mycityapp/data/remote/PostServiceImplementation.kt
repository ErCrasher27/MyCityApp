package com.example.mycityapp.data.remote

import android.location.Location
import android.util.Log
import com.example.mycityapp.data.remote.dto.PostRequest
import com.example.mycityapp.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.BodyProgress.Feature.key
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class PostServiceImplementation (private val client: HttpClient) : PostsService {

    //Will send the API request for actual weather informations
    override suspend fun getWeather(location: Location?): PostResponse {
        return client.get{
            url(HttpRoutesString.returnCurrentWeatherString(location))
        }
    }

    override suspend fun createRequest(PostRequest: PostRequest): PostResponse? {
        return client.post{
            url(HttpRoutesString.CURRENT_WEATHER_JSON)
            contentType(ContentType.Application.Json)
            body = PostRequest
        }
    }

}