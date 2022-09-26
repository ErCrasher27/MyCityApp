package com.example.mycityapp

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mycityapp.data.local.LocalCategoryData.categories
import com.example.mycityapp.data.local.LocalNavigationData
import com.example.mycityapp.data.local.LocalNavigationData.navigationsItems
import com.example.mycityapp.data.local.LocalPlaceData.places
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.ui.MyCityApp
import com.example.mycityapp.ui.MyCityViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
annotation class TestCompactWidth
annotation class TestMediumWidth
annotation class TestExpandedWidth
class MyCityAppScreenNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val viewModel = MyCityViewModel()
    val myCityAppUiState = viewModel.uiState.value
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Test
    @TestCompactWidth
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
    @TestCompactWidth
    fun compactDevice_ClickButton_VerifyClickOnFirstBestRatingsCard() {
        composeTestRule.setContent {
            MyCityApp(WindowWidthSizeClass.Compact)
        }
        composeTestRule.onNodeWithTagForStringId(
            places[0].locationPlace
        ).performTouchInput { click() }
    }
    @Test
    @TestCompactWidth
    fun compactDevice_ClickButton_VerifyClickOnNavigationBottomBar() {
        composeTestRule.setContent {
            MyCityApp(WindowWidthSizeClass.Compact)
        }
        assert(myCityAppUiState.currentTab == CategoryName.Homepage)
        repeat(navigationsItems.size) { count ->
            composeTestRule.onNodeWithContentDescription(
                navigationsItems[count].text.name
            ).performTouchInput { click() }

        }
        //TODO Fix check current tab
        //assertEquals(myCityAppUiState.currentTab, navigationsItems[count].text)
    }
    @Test
    @TestCompactWidth
    fun compactDevice_SwipeLeftUntilLastThenReturns_verifyScrollableCategoriesCard() {
        val lastElementOfCategories = categories.size - 1
        composeTestRule.setContent {
            MyCityApp(
                WindowWidthSizeClass.Compact
            )
        }
        repeat(lastElementOfCategories){ count ->
            composeTestRule.onNodeWithTag(categories[count].nameCategory.name)
                .performTouchInput { swipeLeft() }
        }
        (lastElementOfCategories downTo 0).forEach {
            composeTestRule.onNodeWithTag(categories[it].nameCategory.name)
                .performTouchInput { swipeRight() }
        }
        composeTestRule.onNodeWithTag(categories[0].nameCategory.name)
            .performTouchInput { click() }
        //assertEquals(myCityAppUiState.currentTab, CategoryName.Activities)
    }
    @Test
    @TestCompactWidth
    fun compactDevice_SwipeAndClickButton_VerifyClickOnBestRatingsCard() {
        composeTestRule.setContent {
            MyCityApp(WindowWidthSizeClass.Compact)
        }
        composeTestRule.onNodeWithTagForStringId(places[0].locationPlace)
            .performTouchInput { swipeLeft() }
        composeTestRule.onNodeWithTagForStringId(places[1].locationPlace)
            .performTouchInput { swipeLeft() }
        composeTestRule.onNodeWithTagForStringId(
            places[2].locationPlace
        ).performTouchInput { click() }
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

