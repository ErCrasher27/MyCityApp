package com.example.mycityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycityapp.data.local.LocalPlaceData
import com.example.mycityapp.ui.MyCityApp
import com.example.mycityapp.ui.components.DetailsPlaceCard
import com.example.mycityapp.ui.components.PlaceCard
import com.example.mycityapp.ui.theme.MyCityAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCityAppTheme {
                val windowSize = calculateWindowSizeClass(this)
                MyCityApp(windowSize = windowSize.widthSizeClass)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReplyAppCompactPreview() {
    MyCityAppTheme(darkTheme = true) {
        MyCityApp(
            windowSize = WindowWidthSizeClass.Compact,
        )
    }
}

@Preview(showBackground = true, widthDp = 700)
@Composable
fun ReplyAppMediumPreview() {
    MyCityAppTheme {
        MyCityApp(
            windowSize = WindowWidthSizeClass.Medium,
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
fun ReplyAppExpandedPreview() {
    MyCityAppTheme {
        MyCityApp(
            windowSize = WindowWidthSizeClass.Expanded,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsCardPreview() {
    MyCityAppTheme(darkTheme = true) {
        DetailsPlaceCard(
            place = LocalPlaceData.places[1],
            onClose = {}
        )
    }
}