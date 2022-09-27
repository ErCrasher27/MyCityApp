package com.example.mycityapp.data.remote

import com.example.mycityapp.data.remote.dto.PostRequest
import com.example.mycityapp.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImplementation (private val client: HttpClient) : PostsService {
    override suspend fun getWeather(): PostResponse {
        return client.get{
            url(HttpRoutes.CURRENT_WEATHER_JSON)
        }
    }

    override suspend fun createRequest(PostRequest: PostRequest): PostResponse? {
        return client.post{
            url(HttpRoutes.CURRENT_WEATHER_JSON)
            contentType(ContentType.Application.Json)
            body = PostRequest
        }
    }

}