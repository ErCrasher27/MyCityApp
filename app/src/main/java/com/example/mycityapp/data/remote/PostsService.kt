package com.example.mycityapp.data.remote

import com.example.mycityapp.data.remote.dto.PostRequest
import com.example.mycityapp.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*

interface PostsService {
    suspend fun getWeather(): PostResponse

    suspend fun createRequest(PostRequest: PostRequest): PostResponse?

    companion object {
        fun create(): PostsService{
            return PostServiceImplementation(
                client = HttpClient(Android){

                    install(JsonFeature){
                        serializer = KotlinxSerializer(kotlinx.serialization.json.Json{
                            ignoreUnknownKeys = true
                            isLenient = true})
                    }
                }
            )
        }
    }
}