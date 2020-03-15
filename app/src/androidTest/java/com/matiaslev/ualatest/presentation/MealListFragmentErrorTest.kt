package com.matiaslev.ualatest.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.matiaslev.ualatest.di.testModule
import com.matiaslev.ualatest.di.testModuleError
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.test.KoinTest

class MealListFragmentErrorTest : KoinTest {

    @get:Rule
    val rule = object : ActivityTestRule<MainActivity>(MainActivity::class.java) {
        override fun beforeActivityLaunched() {
            super.beforeActivityLaunched()
            loadKoinModules(testModuleError)
        }
    }

    @Test
    fun mainFragmentErrorTest() {
        onView(withText("upsss!")).check(matches(isDisplayed()))
    }

    @After
    fun unloadModule() {
        unloadKoinModules(testModuleError)
    }
}