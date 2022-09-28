package com.example.mycityapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import com.example.mycityapp.data.remote.PostsService
import com.example.mycityapp.data.remote.dto.PostResponse
import com.example.mycityapp.data.remote.dto.currentWeather
import com.example.mycityapp.ui.MyCityApp
import com.example.mycityapp.ui.theme.MyCityAppTheme

class MainActivity : ComponentActivity() {
    private val serviceWeather = PostsService.create()

    @SuppressLint("ProduceStateDoesNotAssignValue")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val weatherInformation =
                produceState<PostResponse>(
                    initialValue = PostResponse(null), producer = {
                        serviceWeather.getWeather()
                    })

                            MyCityAppTheme {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = androidx.compose.material3.MaterialTheme.colorScheme.background
                        ) {
                            val windowSize = calculateWindowSizeClass(this)
                            MyCityApp(windowSize = windowSize.widthSizeClass)
                        }
                    }
        }
    }
}