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
class LoginAndLogoutTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(NavigationActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule =
        GrantPermissionRule.grant(
            "android.permission.ACCESS_FINE_LOCATION"
        )

    @Test
    fun loginAndLogout() {
        Thread.sleep(2000)
        //2 second wait

        val supportVectorDrawablesButton = onView(
            allOf(
                withId(R.id.phone_button), withText("Sign in with phone"),
                childAtPosition(
                    allOf(
                        withId(R.id.btn_holder),
                        childAtPosition(
                            withId(R.id.container),
                            0
                        )
                    ),
                    0
                )
            )
        )
        //variable for sign in button
        supportVectorDrawablesButton.perform(scrollTo(), click())
        //click sign in button

        Thread.sleep(500)
        //half second wait

        val textInputEditText = onView(
            allOf(
                withId(R.id.phone_number),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.phone_layout),
                        0
                    ),
                    0
                )
            )
        )
        textInputEditText.perform(scrollTo(), replaceText("1111111111"), closeSoftKeyboard())
        //create variable for entering phone number, enter default phone number

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.phone_number), withText("1111111111"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.phone_layout),
                        0
                    ),
                    0
                )
            )
        )
        //replace text with default phone number
        textInputEditText2.perform(pressImeActionButton())

        Thread.sleep(2000)
        //2 second wait

        //*********************************************************************************
        val spacedEditText = onView(
            allOf(
                withId(R.id.confirmation_code), withText("- - - - - -"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.confirmation_code_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        spacedEditText.perform(scrollTo(), replaceText("1 1 1 1 1 1"))

        //need variable getting confirmation code, with the default code i want to enter in
        //perform action
        //*********************************************************************************

        Thread.sleep(500)
        //half second delay

        val appCompatButton = onView(
            allOf(
                withId(R.id.submit_confirmation_code), withText("Continue"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        appCompatButton.perform(scrollTo(), click())
        //create button for continue, press continue


        Thread.sleep(500)
        //half second login pause

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_settings), withContentDescription("Settings"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())
        //create navigation settings instance and click

        Thread.sleep(500)
        //half second wait

        val recyclerView = onView(
            allOf(
                withId(R.id.recycler_view),
                childAtPosition(
                    withId(android.R.id.list_container),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(4, click()))
        //click logout button
        Thread.sleep(500)
        //half second wait
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

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
