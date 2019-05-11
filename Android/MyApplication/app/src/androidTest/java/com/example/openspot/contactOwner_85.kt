package com.example.openspot


import android.support.test.espresso.Espresso.onData
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.scrollTo
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.rule.GrantPermissionRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class contactOwner_85 {

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
    fun contactOwner_85() {
        Thread.sleep(2800)
        //two and half second login pause

        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_reservations), ViewMatchers.withContentDescription("Reservations"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navigation),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())
        //click reservation activity, so we can see that view. this is where people
        //should be able to see driveways that were reserved

        Thread.sleep(5000)
        //five second wait

        //method 7



        //method 6
//        onView(allOf(withId(R.id.recycler_view), withId(android.R.id.list_container))) withEffectiveVisibility(VISIBLE))).perform(click());


//        //method5
//        val recyclerView =
//            onView(allOf(withId(R.id.recycler_view), withId(android.R.id.list_container)))
//                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()));

        //method 4
//        onView(withId(R.id.my_recycler_view)).perform(
//            RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.buttonid)));


        //method 3
//        onView(allof(isDisplayed(), withId(R.id.recycler_view)))
//            .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //method 2
//        onData(withId(R.id.recycler_view))
//            .inAdapterView(withId(R.id.grid_adapter_id))
//            .atPosition(0)
//            .perform(click());

//        method 1
//        val recyclerView = onView(
//            allOf(
//                withId(R.id.recycler_view),
//                childAtPosition(
//                    withId(android.R.id.list_container),
//                    0
//                )
//            )
//        )
//        recyclerView.perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
//

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

