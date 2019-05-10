package com.example.openspot


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LilUzi {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(NavigationActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.CALL_PHONE")

    @Test
    fun lilUzi() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val supportVectorDrawablesButton = onView(
                allOf(withId(R.id.phone_button), withText("Sign in with phone"),
                        childAtPosition(
                                allOf(withId(R.id.btn_holder),
                                        childAtPosition(
                                                withId(R.id.container),
                                                0)),
                                0)))
        supportVectorDrawablesButton.perform(scrollTo(), click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val textInputEditText = onView(
                allOf(withId(R.id.phone_number),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.phone_layout),
                                        0),
                                0)))
        textInputEditText.perform(scrollTo(), replaceText("3333333333"), closeSoftKeyboard())

        val appCompatButton = onView(
                allOf(withId(R.id.send_code), withText("Verify Phone Number"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                2)))
        appCompatButton.perform(scrollTo(), click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(500)

        val spacedEditText = onView(
                allOf(withId(R.id.confirmation_code), withText("- - - - - -"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0)))
        spacedEditText.perform(scrollTo(), replaceText("- - - - - -3"))

        val spacedEditText2 = onView(
                allOf(withId(R.id.confirmation_code), withText("- - - - - -3"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0),
                        isDisplayed()))
        spacedEditText2.perform(closeSoftKeyboard())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(500)

        val spacedEditText3 = onView(
                allOf(withId(R.id.confirmation_code), withText("3 - - - - -"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0)))
        spacedEditText3.perform(scrollTo(), replaceText("33 - - - - -"))

        val spacedEditText4 = onView(
                allOf(withId(R.id.confirmation_code), withText("33 - - - - -"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0),
                        isDisplayed()))
        spacedEditText4.perform(closeSoftKeyboard())

        val spacedEditText5 = onView(
                allOf(withId(R.id.confirmation_code), withText("3 3 - - - -"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0)))
        spacedEditText5.perform(scrollTo(), replaceText("3 33 - - - -"))

        val spacedEditText6 = onView(
                allOf(withId(R.id.confirmation_code), withText("3 33 - - - -"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0),
                        isDisplayed()))
        spacedEditText6.perform(closeSoftKeyboard())

        val spacedEditText7 = onView(
                allOf(withId(R.id.confirmation_code), withText("3 3 3 - - -"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0)))
        spacedEditText7.perform(scrollTo(), replaceText("3 3 33 - - -"))

        val spacedEditText8 = onView(
                allOf(withId(R.id.confirmation_code), withText("3 3 33 - - -"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0),
                        isDisplayed()))
        spacedEditText8.perform(closeSoftKeyboard())

        val spacedEditText9 = onView(
                allOf(withId(R.id.confirmation_code), withText("3 3 3 3 - -"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0)))
        spacedEditText9.perform(scrollTo(), replaceText("3 3 3 33 - -"))

        val spacedEditText10 = onView(
                allOf(withId(R.id.confirmation_code), withText("3 3 3 33 - -"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0),
                        isDisplayed()))
        spacedEditText10.perform(closeSoftKeyboard())

        val spacedEditText11 = onView(
                allOf(withId(R.id.confirmation_code), withText("3 3 3 3 3 -"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0)))
        spacedEditText11.perform(scrollTo(), replaceText("3 3 3 3 33 -"))

        val spacedEditText12 = onView(
                allOf(withId(R.id.confirmation_code), withText("3 3 3 3 33 -"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.confirmation_code_layout),
                                        0),
                                0),
                        isDisplayed()))
        spacedEditText12.perform(closeSoftKeyboard())

        val appCompatButton2 = onView(
                allOf(withId(R.id.submit_confirmation_code), withText("Continue"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(`is`("android.widget.ScrollView")),
                                        0),
                                3)))
        appCompatButton2.perform(scrollTo(), click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val bottomNavigationItemView = onView(
                allOf(withId(R.id.navigation_reservations), withContentDescription("Reservations"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView.perform(click())

        val bottomNavigationItemView2 = onView(
                allOf(withId(R.id.navigation_reservations), withContentDescription("Reservations"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.navigation),
                                        0),
                                1),
                        isDisplayed()))
        bottomNavigationItemView2.perform(click())

        val recyclerView = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)))
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(7000)

        val recyclerView2 = onView(
                allOf(withId(R.id.recycler_view),
                        childAtPosition(
                                withId(android.R.id.list_container),
                                0)))
        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
    }

    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
