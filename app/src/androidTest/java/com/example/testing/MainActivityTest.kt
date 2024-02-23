package com.example.testing

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @Rule
    fun activityRule(): ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkTextView() {
        Espresso.onView(ViewMatchers.withId(R.id.helloTextView))
            .check(ViewAssertions.matches(ViewMatchers.withText(R.string.app_name)))
    }

    @Test
    fun checkButton() {
        Espresso.onView(ViewMatchers.withId(R.id.button))
            .perform(click())
        GlobalScope.launch(Dispatchers.Main) {
            delay(2110)
            Espresso.onView(ViewMatchers.withId(R.id.helloTextView))
                .check(ViewAssertions.matches(ViewMatchers.withText("Hello User!")))
        }
    }

}