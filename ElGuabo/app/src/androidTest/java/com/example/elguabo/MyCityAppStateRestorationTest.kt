package com.example.elguabo

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.StateRestorationTester
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.elguabo.data.MyCityDataProvider
import com.example.elguabo.ui.view.MyCityView
import org.junit.Rule
import org.junit.Test

class MyCityAppStateRestorationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    @TestCompactWidth
    fun compactDevice_selectedTouristPlace_afterConfigChange(){
        // Setup compact window
        val stateRestorationTester = StateRestorationTester(composeTestRule)
        stateRestorationTester.setContent{MyCityView(
            windowsSize = WindowWidthSizeClass.Compact,
            onBackPressed = {}
        )}
        // Given first category is displayed
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                MyCityDataProvider.getCategory()[0].categoryNameId)
        ).assertIsDisplayed()
        // Open recommendation list page of first category
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                MyCityDataProvider.getCategory()[0].categoryNameId)
        ).performClick()
        // Given first recommendation of first category is displayed
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                MyCityDataProvider.getCategory()[0].recommendationList[0].recommendationNameId)
        ).assertIsDisplayed()
        // Open current recommendation page of first recommendation
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                MyCityDataProvider.getCategory()[0].recommendationList[0].recommendationNameId)
        ).performClick()
        // Given current recommendation is displayed
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                MyCityDataProvider.getCategory()[0].recommendationList[0].recommendationDescriptionId)
        ).assertIsDisplayed()
        // Verify the current recommendation page
        composeTestRule.onNodeWithContentDescriptionForStringId(
            R.string.back_button
        ).assertExists()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                MyCityDataProvider.getCategory()[0].recommendationList[0].recommendationDescriptionId)
        ).assertExists()
        // Simulate a config change
        stateRestorationTester.emulateSavedInstanceStateRestore()
        // Verify the category page after change
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                MyCityDataProvider.getCategory()[0].categoryNameId)
        ).assertExists()
    }
}