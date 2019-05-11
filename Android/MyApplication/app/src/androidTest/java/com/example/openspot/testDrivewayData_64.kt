//package com.example.openspot
//
//
//import android.support.test.espresso.Espresso.onView
//import android.support.test.espresso.action.ViewActions.*
//import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
//import android.support.test.espresso.matcher.ViewMatchers.*
//import android.support.test.filters.LargeTest
//import android.support.test.rule.ActivityTestRule
//import android.support.test.rule.GrantPermissionRule
//import android.support.test.runner.AndroidJUnit4
//import android.support.v7.widget.RecyclerView.ViewHolder
//import android.view.View
//import android.view.ViewGroup
//import org.hamcrest.Description
//import org.hamcrest.Matcher
//import org.hamcrest.Matchers.`is`
//import org.hamcrest.Matchers.allOf
//import org.hamcrest.TypeSafeMatcher
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@LargeTest
//@RunWith(AndroidJUnit4::class)
//class testDrivewayData_64 {
//
//    @Rule
//    @JvmField
//    var mActivityTestRule = ActivityTestRule(NavigationActivity::class.java)
//
//    @Rule
//    @JvmField
//    var mGrantPermissionRule =
//        GrantPermissionRule.grant(
//            "android.permission.ACCESS_FINE_LOCATION"
//        )
//
//    @Test
//    fun testDrivewayData_64() {
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(3000)
//
//        val appCompatEditText = onView(
//            allOf(
//                withId(R.id.places_autocomplete_search_input),
//                childAtPosition(
//                    allOf(
//                        withId(R.id.autocomplete_fragment2),
//                        childAtPosition(
//                            withId(R.id.placesContainer2),
//                            0
//                        )
//                    ),
//                    1
//                ),
//                isDisplayed()
//            )
//        )
//        appCompatEditText.perform(click())
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(3000)
//
//        val appCompatEditText2 = onView(
//            allOf(
//                withId(R.id.places_autocomplete_edit_text),
//                childAtPosition(
//                    childAtPosition(
//                        withClassName(`is`("android.widget.LinearLayout")),
//                        0
//                    ),
//                    1
//                ),
//                isDisplayed()
//            )
//        )
//        appCompatEditText2.perform(replaceText("university of"), closeSoftKeyboard())
//
//        val recyclerView = onView(
//            allOf(
//                withId(R.id.places_autocomplete_list),
//                childAtPosition(
//                    withClassName(`is`("android.widget.LinearLayout")),
//                    2
//                )
//            )
//        )
//        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(3000)
//
//        val appCompatImageButton = onView(
//            allOf(
//                withContentDescription("Navigate up"),
//                childAtPosition(
//                    allOf(
//                        withId(R.id.action_bar),
//                        childAtPosition(
//                            withId(R.id.action_bar_container),
//                            0
//                        )
//                    ),
//                    1
//                ),
//                isDisplayed()
//            )
//        )
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(3000)
//
//        val appCompatImageButton2 = onView(
//            allOf(
//                withContentDescription("Navigate up"),
//                childAtPosition(
//                    allOf(
//                        withId(R.id.action_bar),
//                        childAtPosition(
//                            withId(R.id.action_bar_container),
//                            0
//                        )
//                    ),
//                    1
//                ),
//                isDisplayed()
//            )
//        )
////        appCompatImageButton2.perform(click())
//    }
//
//    private fun childAtPosition(
//        parentMatcher: Matcher<View>, position: Int
//    ): Matcher<View> {
//
//        return object : TypeSafeMatcher<View>() {
//            override fun describeTo(description: Description) {
//                description.appendText("Child at position $position in parent ")
//                parentMatcher.describeTo(description)
//            }
//
//            public override fun matchesSafely(view: View): Boolean {
//                val parent = view.parent
//                return parent is ViewGroup && parentMatcher.matches(parent)
//                        && view == parent.getChildAt(position)
//            }
//        }
//    }
//}
