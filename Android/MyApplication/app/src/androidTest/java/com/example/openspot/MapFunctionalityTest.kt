package com.example.openspot


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
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
class MapFunctionalityTest {

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
    fun mapFunctionalityTest() {
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

        Thread.sleep(1500)
        //1.5 second login pause


        val imageView2 = onView(
            allOf(
                withContentDescription("Zoom in"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView2.perform(click())
        imageView2.perform(click())
        //double click
        imageView2.perform(click())
        //triple click

        Thread.sleep(3000)
        //3 second login pause


        val imageView3 = onView(
            allOf(
                withContentDescription("Zoom out"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.RelativeLayout")),
                        2
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        imageView3.perform(click())
        imageView3.perform(click())
        imageView3.perform(click())
        imageView3.perform(click())
        imageView3.perform(click())
        imageView3.perform(click())
        imageView3.perform(click())
        imageView3.perform(click())
        imageView3.perform(click())
        imageView3.perform(click())
        imageView3.perform(click())
        imageView3.perform(click())
        //12 clicks zoom out, to test current location zoom in


        Thread.sleep(3000)
        //3 second login pause


        val imageView4 = onView(
            allOf(
                withContentDescription("My Location"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.FrameLayout")),
                        2
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        imageView4.perform(click())
        Thread.sleep(5000)
        //5 second login pause
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
