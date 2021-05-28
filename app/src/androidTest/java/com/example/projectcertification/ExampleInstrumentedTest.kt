package com.example.projectcertification

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.projectcertification.view.activities.MainActivity
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.projectcertification", appContext.packageName)
    }


    @Rule
    @JvmField
    val mActivityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {


    }

    /**
     * test for a view
     */
    @Test
    fun testMainActivityClickView() {
        mActivityRule.scenario.onActivity {
            val v = it?.findViewById(R.id.fab) as View
            assertNotNull(v)
        }


    }


    @Test
    fun onActivityLaunch(){
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.button)).check(matches(isDisplayed()))
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.fab)).check(matches(isDisplayed()))
    }

    @Test
    fun testInputOutput(){
        onView(withId(R.id.fab)).perform(click())
        onView(withId(R.id.et)).perform(typeText("This is automated Test"))
        onView(withId(R.id.button)).perform(click())
    }
}