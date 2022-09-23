package com.example.mycityapp

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mycityapp.ui.MyCityApp
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MyCityAppScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Test
    fun compactDevice_verifyUsingBottomNavigation() {
        composeTestRule.setContent {
            MyCityApp(WindowWidthSizeClass.Compact)
        }
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_bottom
        ).assertExists()
    }

    @Test
    fun mediumDevice_verifyUsingNavigationRail() {
        // Set up medium window
        composeTestRule.setContent {
            MyCityApp(
                windowSize = WindowWidthSizeClass.Medium
            )
        }
        // Navigation rail is displayed
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_rail
        ).assertExists()
    }
    @Test
    fun expandedDevice_verifyUsingNavigationDrawer() {
        // Set up expanded window
        composeTestRule.setContent {
            MyCityApp(
                windowSize = WindowWidthSizeClass.Expanded
            )
        }
        // Navigation drawer is displayed
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_drawer
        ).assertExists()
    }
}
