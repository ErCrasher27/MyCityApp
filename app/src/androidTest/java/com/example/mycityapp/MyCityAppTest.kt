package com.example.mycityapp

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mycityapp.data.local.LocalCategoryData.categories
import com.example.mycityapp.data.local.LocalNavigationData.navigationsItems
import com.example.mycityapp.data.local.LocalPlaceData.places
import com.example.mycityapp.data.model.CategoryName
import com.example.mycityapp.ui.MyCityApp
import com.example.mycityapp.ui.MyCityViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)

//Annotation classes for different screen sizes tests//
annotation class TestCompactWidth
annotation class TestMediumWidth
annotation class TestExpandedWidth

//ViewModel declaration for checks in the UiState variables//
private val viewModel = MyCityViewModel()
private val myCityAppUiState = viewModel.uiState.value

class MyCityAppScreenNavigationTest {

    /*Creating a ComposeTestRule that allows you to test and control
    composables and applications using Compose*/
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    val lastElementOfCategories = categories.size - 1
    //Will check if the bottom navigation bar is displayed when the device is compact//
    @Test
    @TestCompactWidth
    fun compactDevice_verifyBottomNavigation() {
        //Set up Compact window size
        setWindowWidthSizeCompact()
        //Check if navigation bottom is displayed
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_bottom
        ).assertExists()
    }

    /*Will check if the bottom navigation bar is a navigation
      rail and it is displayed when the device is medium    */
    @Test
    @TestMediumWidth
    fun mediumDevice_verifyNavigationRail() {
        //Set up Medium window size
        setWindowWidthSizeMedium()
        //Check if navigation rail is displayed
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_rail
        ).assertExists()
    }

    /*Will check if the bottom navigation bar is a navigation
      drawer and it is displayed when the device is Expanded*/
    @Test
    @TestExpandedWidth
    fun expandedDevice_verifyNavigationDrawer() {
        //Set up Expanded window size
        setWindowWidthSizeExpanded()
        //Check if navigation drawer is displayed
        composeTestRule.onNodeWithTagForStringId(
            R.string.navigation_drawer
        ).assertExists()
    }

    /*Will verify if the user click on the first best ratings card works and
      controls if the more details tab is open by checking if the Close button exists*/
    //TODO Fix bug that will not show place detail if the Compact phone is in landscape mode
    @Test
    @TestCompactWidth
    fun compactDevice_ClickButton_VerifyClickOnFirstBestRatingsCard() {
        //Set up Compact window size
        setWindowWidthSizeCompact()
        //Click the first Best Places Card
        clickButtonBestPlacesCard(index = 0)
        //Check if the Close Button is Displayed
        assertIsDisplayedCloseButtonPlaceDetails()
    }

    /*Will verify that the user can click all the options on bottom navigation bar*/
    @Test
    @TestCompactWidth
    fun compactDevice_ClickButton_VerifyClickOnNavigationBottomBar() {
        //Set up Compact window size
        setWindowWidthSizeCompact()
        //Check if the current tab is Homepage
        checkCurrentTabIsSelected(CategoryName.Homepage)

        //TODO Fix check current tab
        //assertEquals(myCityAppUiState.currentTab, navigationsItems[count].text)

        //Click all the elements in the bottom navigation bar
        clickAllNavigationBarElements(navigationsItems.size)

    }

    /*Will verify if the user can swipe left until the last element of the Categories Card*/
    @Test
    @TestCompactWidth
    fun compactDevice_SwipeLeftUntilLastThenReturns_verifyScrollableCategoriesCard() {
        //Set up Compact window size
        setWindowWidthSizeCompact()
        //Swipe Left to the Categories card until last element
        swipeLeftCategoriesToLastElement(lastElementOfCategories)
        //Swipe Right to the Categories card until last element
        swipeRightCategoriesToLastElement(lastElementOfCategories, 0)
        //Click the first categories Card to check if the user after the swipe can click
        clickSpecificCategory(0)
        //assertEquals(myCityAppUiState.currentTab, CategoryName.Activities)
    }

    /*Will verify if the user can swipe the best places card
      and then click the button for more details on the place*/
    //TODO Refactor this test because it get all the places and not the best ones
    @Test
    @TestCompactWidth
    fun compactDevice_SwipeAndClickButton_VerifyClickOnBestRatingsCard() {
        //Set up Compact window size
        setWindowWidthSizeCompact()
        //Swipe Left to the Best Places card
        swipeLeftPlaceTo(2)
        //Click the third element of the Best Places card
        clickButtonBestPlacesCard(2)
    }
    @Test
    @TestCompactWidth
    fun compactDevice_ClickCallButtonWorking_VerifyUserClickCallButton() {
        //Set up Compact window size
        setWindowWidthSizeCompact()
        clickButtonBestPlacesCard(0)
        clickCallButton()
    }
    @Test
    @TestCompactWidth
    fun compactDevice_ClickCallButtonNOTWorking_VerifyUserClickCallButton() {
        //Set up Compact window size
        setWindowWidthSizeCompact()
        clickSpecificNavigationBarElement(3)
        clickButtonBestPlacesCard(2)
        clickCallButton()
        assertIsDisplayedCloseButtonPlaceDetails()
    }

    @Test
    @TestMediumWidth
    fun mediumDevice_ClickButton_VerifyClickOnFirstBestRatingsCard() {
        //Set up Medium window size
        setWindowWidthSizeMedium()
        //Click the first Best Places Card
        clickButtonBestPlacesCard(index = 0)
        //Check if the Close Button is Displayed
        assertIsDisplayedCloseButtonPlaceDetails()
    }

    @Test
    @TestMediumWidth
    fun mediumDevice_ClickButton_VerifyClickOnNavigationBottomBar() {
        //Set up Medium window size
        setWindowWidthSizeMedium()
        //Check if the current tab is Homepage
        checkCurrentTabIsSelected(CategoryName.Homepage)

        //TODO Fix check current tab
        //assertEquals(myCityAppUiState.currentTab, navigationsItems[count].text)

        //Click all the elements in the navigation rail bar
        clickAllNavigationBarElements(navigationsItems.size)
    }

    @Test
    @TestMediumWidth
    fun mediumDevice_SwipeLeftUntilLastThenReturns_verifyScrollableCategoriesCard() {
        //Set up Medium window size
        setWindowWidthSizeMedium()
        //Swipe Left to the Categories card until last element
        swipeLeftCategoriesToLastElement(lastElementOfCategories)
        //Swipe Right to the Categories card until last element
        swipeRightCategoriesToLastElement(lastElementOfCategories, 0)
        //Click the first categories Card to check if the user after the swipe can click
        clickSpecificCategory(0)
        //assertEquals(myCityAppUiState.currentTab, CategoryName.Activities)
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_ClickButton_VerifyClickOnFirstBestRatingsCard() {
        //Set up Expanded window size
        setWindowWidthSizeExpanded()
        //Click the first Best Places Card
        clickButtonBestPlacesCard(index = 0)
        //Check if the Image is Displayed
        assertIsPlaceCardDetailsImageDisplayed(0)
    }

    @Test
    @TestExpandedWidth
    fun expandedDevice_ClickButton_VerifyClickOnNavigationBottomBar() {
        //Set up Expanded window size
        setWindowWidthSizeExpanded()
        //Check if the current tab is Homepage
        checkCurrentTabIsSelected(CategoryName.Homepage)

        //TODO Fix check current tab
        //assertEquals(myCityAppUiState.currentTab, navigationsItems[count].text)

        //Click all the elements in the navigation drawer bar
        clickAllNavigationBarElements(navigationsItems.size)
    }
    @Test
    @TestExpandedWidth
    fun expandedDevice_SwipeLeftUntilLastThenReturns_verifyScrollableCategoriesCard() {
        //Set up Expanded window size
        setWindowWidthSizeExpanded()
        //Swipe Left to the Categories card until last element
        swipeLeftCategoriesToLastElement(lastElementOfCategories)
        //Swipe Right to the Categories card until last element
        swipeRightCategoriesToLastElement(lastElementOfCategories, 0)
        //Click the first categories Card to check if the user after the swipe can click
        clickSpecificCategory(0)
        //assertEquals(myCityAppUiState.currentTab, CategoryName.Activities)
    }


    fun clickSpecificCategory(index: Int){
        composeTestRule.onNodeWithTag(categories[index].nameCategory.name)
            .performTouchInput { click() }
    }
    fun setWindowWidthSizeCompact() {
        composeTestRule.setContent {
            MyCityApp(WindowWidthSizeClass.Compact)
        }
    }

    fun setWindowWidthSizeMedium() {
        composeTestRule.setContent {
            MyCityApp(WindowWidthSizeClass.Medium)
        }
    }

    fun setWindowWidthSizeExpanded() {
        composeTestRule.setContent {
            MyCityApp(WindowWidthSizeClass.Expanded)
        }
    }

    fun clickButtonBestPlacesCard(index: Int) {
        composeTestRule.onNodeWithTagForStringId(
            places[index].locationPlace
        ).performTouchInput { click() }
    }

    fun assertIsDisplayedCloseButtonPlaceDetails() {
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.close_details_place
        ).assertIsDisplayed()
    }

    fun checkCurrentTabIsSelected(categoryName: CategoryName) {
        assert(myCityAppUiState.currentTab == categoryName)
    }

    fun clickAllNavigationBarElements(size: Int) {
        repeat(size) { count ->
            clickSpecificNavigationBarElement(count)
        }
    }

    fun clickSpecificNavigationBarElement(indexNavItem: Int) {
        composeTestRule.onNodeWithContentDescription(
            navigationsItems[indexNavItem].text.name
        ).performTouchInput { click() }
    }

    fun swipeLeftCategoriesToLastElement(sizeCategories: Int) {
        repeat(sizeCategories) { count ->
            swipeLeftCategories(count)
        }
    }
    fun swipeLeftPlaceTo(sizePlaces: Int){
        repeat(sizePlaces){ count ->
            swipeLeftPlace(count)
        }
    }
    fun swipeLeftPlace(count: Int){
        composeTestRule.onNodeWithTagForStringId(places[count].locationPlace)
            .performTouchInput { swipeLeft() }
    }
    fun swipeLeftCategories(index: Int) {
        composeTestRule.onNodeWithTag(categories[index].nameCategory.name)
            .performTouchInput { swipeLeft() }
    }

    fun swipeRightCategoriesToLastElement(sizeCategories: Int, finish: Int) {
        (sizeCategories downTo finish).forEach {
            swipeRightCategories(it)
        }
    }

    fun swipeRightCategories(index: Int) {
        composeTestRule.onNodeWithTag(categories[index].nameCategory.name)
            .performTouchInput { swipeRight() }
    }
    fun clickCallButton(){
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.call_place
        ).performTouchInput { click() }
    }
    fun assertIsPlaceCardDetailsImageDisplayed(namePlaceIndex: Int){
        composeTestRule.onNodeWithContentDescriptionForStringId(
            places[namePlaceIndex].name
        ).assertIsDisplayed()
    }
}