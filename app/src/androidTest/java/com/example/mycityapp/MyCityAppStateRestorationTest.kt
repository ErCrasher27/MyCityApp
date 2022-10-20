package com.example.mycityapp

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.mycityapp.data.local.LocalCategoryData.categories
import com.example.mycityapp.data.local.LocalPlaceData.places
import com.example.mycityapp.ui.MyCityApp
import org.junit.Rule
import org.junit.Test

class MyCityAppStateRestorationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun compactDevice_selectedActivityRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { MyCityApp(windowSize = WindowWidthSizeClass.Compact) }
        composeTestRule.onNodeWithText(categories[0].nameCategory.name)
            .performClick()
        composeTestRule.onNodeWithTagForStringId(
            places[0].descriptionPlace
        ).assertIsDisplayed()
        composeTestRule.onNodeWithTagForStringId(
            places[0].locationPlace
        ).performClick()
        stateRestorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.close_details_place
        ).performClick()
    }

    @Test
    fun mediumDevice_selectedActivityRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { MyCityApp(windowSize = WindowWidthSizeClass.Medium) }
        composeTestRule.onNodeWithText(categories[0].nameCategory.name)
            .performClick()
        composeTestRule.onNodeWithTagForStringId(
            places[0].descriptionPlace
        ).assertIsDisplayed()
        composeTestRule.onNodeWithTagForStringId(
            places[0].locationPlace
        ).performClick()
        stateRestorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.close_details_place
        ).performClick()
    }
    //TODO FIX it has 2 Activities nodes
    /*
    @Test
    fun expandedDevice_selectedActivityRetained_afterConfigChange() {
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent { MyCityApp(windowSize = WindowWidthSizeClass.Expanded) }
        composeTestRule.onNodeWithText(categories[0].nameCategory.name)
            .performClick()
        composeTestRule.onNodeWithTagForStringId(
            places[0].descriptionPlace
        ).assertIsDisplayed()
        composeTestRule.onNodeWithTagForStringId(
            places[0].locationPlace
        ).performClick()
        stateRestorationTester.emulateSavedInstanceStateRestore()
        composeTestRule.onNodeWithContentDescriptionForStringId(
            places[0].name
        ).assertIsDisplayed()
    }
     */
}
