package com.techethic.compose.dailycounter

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.techethic.compose.dailycounter.data.model.Counter
import com.techethic.compose.dailycounter.theme.MyTheme
import com.techethic.compose.dailycounter.ui.counter.DailyCounter
import org.junit.Rule
import org.junit.Test

class CounterTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun MyTest() {
        // Start the app
        val initValue =  Counter(0, "20210808")
        val counter = mutableStateOf( Counter(0, "20210808") )
        composeTestRule.setContent {
            MyTheme {
                DailyCounter(counter.value){
                    counter.value =  Counter(1, initValue.date)
                }
            }
        }

        composeTestRule.onNodeWithText("0").assertIsDisplayed()
        composeTestRule.onNodeWithText("0").performClick()
        composeTestRule.onNodeWithText("1").assertIsDisplayed()

    }
}