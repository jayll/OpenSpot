//package com.example.openspot
//
//
//import android.support.test.espresso.Espresso.onData
//import android.support.test.espresso.Espresso.onView
//import android.support.test.espresso.action.ViewActions.*
//import android.support.test.espresso.assertion.ViewAssertions.doesNotExist
//import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
//import android.support.test.espresso.matcher.PreferenceMatchers
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
//class testDrivewayViewEditDelete_61_62_63 {
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
//    fun testDrivewayViewEdittttDelete_61_62_63() {
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(3000)
//
//        val supportVectorDrawablesButton = onView(
//            allOf(
//                withId(R.id.phone_button), withText("Sign in with phone"),
//                childAtPosition(
//                    allOf(
//                        withId(R.id.btn_holder),
//                        childAtPosition(
//                            withId(R.id.container),
//                            0
//                        )
//                    ),
//                    0
//                )
//            )
//        )
//        supportVectorDrawablesButton.perform(scrollTo(), click())
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(3000)
//
//        val textInputEditText = onView(
//            allOf(
//                withId(R.id.phone_number),
//                childAtPosition(
//                    childAtPosition(
//                        withId(R.id.phone_layout),
//                        0
//                    ),
//                    0
//                )
//            )
//        )
//        textInputEditText.perform(scrollTo(), replaceText("7"), closeSoftKeyboard())
//
//        val appCompatButton = onView(
//            allOf(
//                withId(R.id.send_code), withText("Verify Phone Number"),
//                childAtPosition(
//                    childAtPosition(
//                        withClassName(`is`("android.widget.ScrollView")),
//                        0
//                    ),
//                    2
//                )
//            )
//        )
//        appCompatButton.perform(scrollTo(), click())
//
//        val textInputEditText2 = onView(
//            allOf(
//                withId(R.id.phone_number), withText("7"),
//                childAtPosition(
//                    childAtPosition(
//                        withId(R.id.phone_layout),
//                        0
//                    ),
//                    0
//                )
//            )
//        )
//        textInputEditText2.perform(scrollTo(), replaceText("7186"))
//
//        val textInputEditText3 = onView(
//            allOf(
//                withId(R.id.phone_number), withText("7186"),
//                childAtPosition(
//                    childAtPosition(
//                        withId(R.id.phone_layout),
//                        0
//                    ),
//                    0
//                ),
//                isDisplayed()
//            )
//        )
//        textInputEditText3.perform(closeSoftKeyboard())
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(3000)
//
//        val textInputEditText4 = onView(
//            allOf(
//                withId(R.id.phone_number), withText("7186"),
//                childAtPosition(
//                    childAtPosition(
//                        withId(R.id.phone_layout),
//                        0
//                    ),
//                    0
//                )
//            )
//        )
//        textInputEditText4.perform(scrollTo(), replaceText("718614576"))
//
//        val textInputEditText5 = onView(
//            allOf(
//                withId(R.id.phone_number), withText("718614576"),
//                childAtPosition(
//                    childAtPosition(
//                        withId(R.id.phone_layout),
//                        0
//                    ),
//                    0
//                ),
//                isDisplayed()
//            )
//        )
//        textInputEditText5.perform(closeSoftKeyboard())
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(3000)
//
//        val textInputEditText6 = onView(
//            allOf(
//                withId(R.id.phone_number), withText("718614576"),
//                childAtPosition(
//                    childAtPosition(
//                        withId(R.id.phone_layout),
//                        0
//                    ),
//                    0
//                )
//            )
//        )
//        textInputEditText6.perform(scrollTo(), replaceText("7186145768"))
//
//        val textInputEditText7 = onView(
//            allOf(
//                withId(R.id.phone_number), withText("7186145768"),
//                childAtPosition(
//                    childAtPosition(
//                        withId(R.id.phone_layout),
//                        0
//                    ),
//                    0
//                ),
//                isDisplayed()
//            )
//        )
//        textInputEditText7.perform(closeSoftKeyboard())
//
//        val appCompatButton2 = onView(
//            allOf(
//                withId(R.id.send_code), withText("Verify Phone Number"),
//                childAtPosition(
//                    childAtPosition(
//                        withClassName(`is`("android.widget.ScrollView")),
//                        0
//                    ),
//                    2
//                )
//            )
//        )
//        appCompatButton2.perform(scrollTo(), click())
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(3000)
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(3000)
//
//        val bottomNavigationItemView = onView(
//            allOf(
//                withId(R.id.navigation_settings), withContentDescription("Settings"),
//                childAtPosition(
//                    childAtPosition(
//                        withId(R.id.navigation),
//                        0
//                    ),
//                    2
//                ),
//                isDisplayed()
//            )
//        )
//        bottomNavigationItemView.perform(click())
//
//        val recyclerView = onView(withText("List Your Driveway"))
//        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))
////        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(3000)
//
//        val actionMenuItemView = onView(
//            allOf(
//                withId(R.id.drivewayAddButton), withContentDescription("addButton"),
//                childAtPosition(
//                    childAtPosition(
//                        withId(R.id.action_bar),
//                        2
//                    ),
//                    0
//                ),
//                isDisplayed()
//            )
//        )
//        actionMenuItemView.perform(click())
//
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
//                        withId(R.id.autocomplete_fragment),
//                        childAtPosition(
//                            withId(R.id.placesContainer),
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
//        Thread.sleep(7000)
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
//        appCompatEditText2.perform(replaceText("university at"), closeSoftKeyboard())
//
//        val recyclerView2 = onView(
//            allOf(
//                withId(R.id.places_autocomplete_list),
//                childAtPosition(
//                    withClassName(`is`("android.widget.LinearLayout")),
//                    2
//                )
//            )
//        )
//        recyclerView2.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(7000)
//
//        val switch_ = onView(
//            allOf(
//                withId(R.id.on_off_switch), withText("Inactive"),
//                childAtPosition(
//                    childAtPosition(
//                        withId(android.R.id.content),
//                        0
//                    ),
//                    4
//                ),
//                isDisplayed()
//            )
//        )
//        switch_.perform(click())
//
//        val appCompatImageButton = onView(
//            allOf(
//                withId(R.id.checkmark),
//                childAtPosition(
//                    childAtPosition(
//                        withId(android.R.id.content),
//                        0
//                    ),
//                    5
//                ),
//                isDisplayed()
//            )
//        )
//        appCompatImageButton.perform(click())
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(7000)
//
//        val recyclerView3 = onView(
//            allOf(
//                withId(R.id.recycler_view),
//                childAtPosition(
//                    withId(android.R.id.list_container),
//                    0
//                )
//            )
//        )
//        recyclerView3.perform(actionOnItemAtPosition<ViewHolder>(1, click()))
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(7000)
//
//        val appCompatImageButton2 = onView(
//            allOf(
//                withId(R.id.checkmark),
//                childAtPosition(
//                    childAtPosition(
//                        withId(android.R.id.content),
//                        0
//                    ),
//                    5
//                ),
//                isDisplayed()
//            )
//        )
//        appCompatImageButton2.perform(click())
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(7000)
//
//        val recyclerView4 = onView(
//            allOf(
//                withId(R.id.recycler_view),
//                childAtPosition(
//                    withId(android.R.id.list_container),
//                    0
//                )
//            )
//        )
//        recyclerView4.perform(actionOnItemAtPosition<ViewHolder>(1, click()))
//
//        // Added a sleep statement to match the app's execution delay.
//        // The recommended way to handle such scenarios is to use Espresso idling resources:
//        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
//        Thread.sleep(7000)
//
//        val appCompatButton3 = onView(
//            allOf(
//                withText("Delete"),
//                childAtPosition(
//                    childAtPosition(
//                        withId(android.R.id.content),
//                        0
//                    ),
//                    6
//                ),
//                isDisplayed()
//            )
//        )
//        appCompatButton3.perform(click())
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
