package com.ojarrett.myrunningroutes

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Test

import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.equalTo
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
            .check(matches(withTagValue(equalTo(R.drawable.run_selector))))

        // User clicks one of the circles and observes that it turns white
        onView(withId(R.id.circle_run_1))
            .perform(click())
            .check(matches(withTagValue(equalTo(R.drawable.run_selector_white))))

        // User clicks a different circle and observes that the originally selected circle
        // turns grey and the newly selected circle turns white
        onView(withId(R.id.circle_run_2))
            .perform(click())
            .check(matches(withTagValue(equalTo(R.drawable.run_selector_white))))

        onView(withId(R.id.circle_run_1))
            .check(matches(withTagValue(equalTo(R.drawable.run_selector))))
    }

    @Test
    fun clickRunSelectorActions() {
        // User loads app and clicks one of the grey circles to record a new run
        onView(withId(R.id.circle_run_1))
            .perform(click())

        // User clicks the "Start" button to start recording their run and observes that
        // the selected circle turns green
        onView(withId(R.id.button_start)).perform(click())
        onView(withId(R.id.circle_run_1))
            .check(matches(withTagValue(equalTo(R.drawable.run_selector_green))))

        // User clicks the "Pause" button and observes that the selected circle turns orange
        onView(withId(R.id.button_pause)).perform(click())
        onView(withId(R.id.circle_run_1))
            .check(matches(withTagValue(equalTo(R.drawable.run_selector_orange))))

        // User clicks the "End" button and observes that the selected circle turns red
        onView(withId(R.id.button_end)).perform(click())
        onView(withId(R.id.circle_run_1))
            .check(matches(withTagValue(equalTo(R.drawable.run_selector_red))))

        // User clicks the "Reset" button and observes that the selected circle turns back to
        // grey
        onView(withId(R.id.button_reset)).perform(click())
        onView(withId(R.id.circle_run_1))
            .check(matches(withTagValue(equalTo(R.drawable.run_selector))))
    }
}