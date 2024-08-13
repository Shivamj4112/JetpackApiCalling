package com.example.jetpackapicallingmvvm

import androidx.compose.runtime.currentComposer
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import org.junit.Test

class HomeScreenScreenshotTest : ScreenshotTest{

    @Test
    fun homeScreenTest() {

        createComposeRule().setContent {

            EmployeeCard(
                profileImage = R.drawable.ic_launcher_background,
                name = "John Doe",
                age = "30",
                salary = "5000",
            )
        }
        compareScreenshot(createComposeRule())
    }
}