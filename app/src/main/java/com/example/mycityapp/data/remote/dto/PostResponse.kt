package com.example.mycityapp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val current: currentWeather?
)
@Serializable
data class currentWeather(
    val last_updated_epoch: Int,
    val last_updated: String,
)
