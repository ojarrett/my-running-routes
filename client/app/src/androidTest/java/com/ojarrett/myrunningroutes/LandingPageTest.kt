package com.ojarrett.myrunningroutes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withResourceName
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Test

import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LandingPageTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickRunSelectorBasic() {
        // User loads app and should see 3 grey circles indicating the run to select
        onView(withId(R.id.circle_run_1))
            .perform(click())
            .check(matches(withResourceName("run_selector.xml")))

        // User clicks one of the circles and observes that it turns white

        // User clicks a different circle and observes that the originally selected circle
        // turns grey and the newly selected circle turns white
    }

    @Test
    fun clickRunSelectorActions() {
        // User loads app and clicks one of the grey circles to record a new run
        onView(withId(R.id.circle_run_1))
            .perform(click())
            .check(matches(withResourceName("run_selector.xml")))

        // User clicks the "Start" button to start recording their run and observes that
        // the selected circle turns green

        // User clicks the "Pause" button and observes that the selected circle turns orange

        // User clicks the "End" button and observes that the selected circle turns red

        // User clicks the "Reset" button and observes that the selected circle turns back to
        // grey
    }
}