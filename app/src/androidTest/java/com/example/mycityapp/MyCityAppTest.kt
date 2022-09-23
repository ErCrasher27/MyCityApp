package com.example.mycityapp

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeLeft
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mycityapp.data.local.LocalCategoryData.categories
import com.example.mycityapp.ui.MyCityApp
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

    @Test
    fun compactDevice_ScrollToLeftUntilLast_verifyScrollableCategoriesCard() {
        // Set up expanded window
        val lastElementOfCategories = categories.size - 1
        composeTestRule.setContent {
            MyCityApp(
                windowSize = WindowWidthSizeClass.Compact
            )
        }
        // Navigation drawer is displayed
        repeat(lastElementOfCategories){ count ->
            composeTestRule.onNodeWithTag(categories[count].nameCategory.name)
                .performTouchInput { swipeLeft() }
        }
        composeTestRule.onNodeWithTag(categories[lastElementOfCategories].nameCategory.name)
            .performTouchInput { click() }
        }
}

/*
@Test
fun compactDevice_ButtonClickNavigationBar_navigateToActivitiesAndOpeningFirstPlace() {
    composeTestRule.setContent {
        MyCityApp(
            windowSize = WindowWidthSizeClass.Compact
        )
    }
    // Navigation drawer is displayed
    composeTestRule.onNodeWithContentDescription(
        navigationsItems[0].text.name
    ).performClick()
    composeTestRule.onNodeWithContentDescriptionForStringId(
        LocalPlaceData.places[0].locationPlace
    ).performClick()
}
*/

