package com.example.mycityapp.data.remote

import android.location.Location
import com.example.mycityapp.data.remote.dto.PostRequest
import com.example.mycityapp.data.remote.dto.PostResponse
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*

class PostServiceImplementation(private val client: HttpClient) : PostsService {
    //Will send the API request for actual weather information
    override suspend fun getWeather(location: Location?): PostResponse? {
        return try {
            client.get {
                url(HttpRoutesString.returnCurrentWeatherString(location))
            }
        } catch (e: RedirectResponseException) {
            //Catch 3xx Errors
            println("Error ${e.response.status.description}")
            null
        } catch (e: ClientRequestException) {
            //Catch 4xx Errors
            println("Error ${e.response.status.description}")
            null
        } catch (e: ServerResponseException) {
            //Catch 5xx Errors
            println("Error ${e.response.status.description}")
            null
        } catch (e: Exception) {
            println("Error $e")
            null
        }
    }

    override suspend fun createRequest(PostRequest: PostRequest): PostResponse? {
        return client.post {
            try {
                url(HttpRoutesString.CURRENT_WEATHER_JSON)
                contentType(ContentType.Application.Json)
                body = PostRequest
            } catch (e: RedirectResponseException) {
                //Catch 3xx Errors
                println("Error ${e.response.status.description}")
                null
            } catch (e: ClientRequestException) {
                //Catch 4xx Errors
                println("Error ${e.response.status.description}")
                null
            } catch (e: ServerResponseException) {
                //Catch 5xx Errors
                println("Error ${e.response.status.description}")
                null
            } catch (e: Exception) {
                println("Error $e")
                null
            }
        }
    }

}