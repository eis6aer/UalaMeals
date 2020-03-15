package com.matiaslev.ualatest.presentation

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.matiaslev.ualatest.di.testModule
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest

class SecondaryFragmentTest : KoinTest {

    @get:Rule
    val rule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            loadKoinModules(testModule)
        }
    }

    @Test
    fun mainFragmentSuccessTest() {
        onView(withText("pastas")).perform(click())
        onView(withText("italianas"))
            .check(matches(isDisplayed()))
    }

    @After
    fun unloadModule() {
        unloadKoinModules(testModule)
    }
}