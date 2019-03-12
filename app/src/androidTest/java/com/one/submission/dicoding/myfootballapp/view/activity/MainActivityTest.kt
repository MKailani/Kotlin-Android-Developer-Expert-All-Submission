package com.one.submission.dicoding.myfootballapp.view.activity

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.one.submission.dicoding.myfootballapp.R.id.*
import com.one.submission.dicoding.myfootballapp.utils.espresso.EspressoIdlingResource
import junit.framework.AssertionFailedError
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Dicoding Academy
 *
 * Submission 4
 * Kotlin Android Developer Expert (KADE)
 *
 * Created by kheys on 06/02/19.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, true, true)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }



    /**
     * UI Testing
     * Check Detail Menu from (Last Match, Next Match & Favorite)
     * List Item For All Menu
     */
    @Test
    fun menuDetailWithAllMenuUITest() {
        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Click Detail from Last Match Menu
        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, ViewActions.click())
        )

        // Show Detail Match
        onView(ViewMatchers.withId(iv_team_home)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(iv_team_away)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()

        // Next Match Menu
        onView(ViewMatchers.withId(bottom_next_match)).perform(ViewActions.click())

        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Click Detail from Next Match Menu
        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(2, ViewActions.click())
        )

        // Show Detail Match
        onView(ViewMatchers.withId(iv_team_home)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(iv_team_away)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.pressBack()

        // Favorite Match Menu
        onView(ViewMatchers.withId(bottom_favorite)).perform(ViewActions.click())

        try { // if any item for favorite menu
            onView(ViewMatchers.withId(recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

            // Click Detail from Menu Favorite
            onView(ViewMatchers.withId(recyclerView)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click())
            )

            // Show Detail Match
            onView(ViewMatchers.withId(iv_team_home)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            onView(ViewMatchers.withId(iv_team_away)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

            Espresso.pressBack()

        } catch (e: AssertionFailedError) { // if no item for favorite menu
            onView(ViewMatchers.withId(recyclerView))
                .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
        }

        // Last Match Menu
        onView(ViewMatchers.withId(bottom_prev_match)).perform(ViewActions.click())

        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

    /**
     * UI Testing
     * Add Favorite item from Last Match & check menu Favorite show detail remove favorite item
     * List Item For All Menu
     */
    @Test
    fun addOrRemoveFavoriteUITest() {

        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Click Detail from Last Match Menu
        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, ViewActions.click())
        )

        // Show Detail Match
        onView(ViewMatchers.withId(iv_team_home)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(iv_team_away)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        // Perfome Favorite Click Add Favorite
        onView(ViewMatchers.withId(action_menu_favorite)).perform(ViewActions.click())

        Espresso.pressBack()

        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Show Favorite Menu
        onView(ViewMatchers.withId(bottom_favorite)).perform(ViewActions.click())

        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Click Detail from Last Match Menu
        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click())
        )

        // Show Detail Match
        onView(ViewMatchers.withId(iv_team_home)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(iv_team_away)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Perfome Favorite Click Remove Favorite
        onView(ViewMatchers.withId(action_menu_favorite)).perform(ViewActions.click())

        Espresso.pressBack()

        try{
            onView(ViewMatchers.withId(recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        } catch (e: AssertionFailedError) { // if no item for favorite menu
            onView(ViewMatchers.withId(recyclerView))
                .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
        }
    }

    /**
     * UI Testing
     * Full testing
     * 1. Add or remove favorite
     * 2. detail menu
     * 3. bottom menu
     * 4. show list item Recycler
     */
    @Test
    fun fullUITest() {
        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                4
            )
        )

        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(3, ViewActions.click())
        )

        // Show Detail Match
        onView(ViewMatchers.withId(iv_team_home)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(iv_team_away)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


        // Perfome Favorite Click
        onView(ViewMatchers.withId(action_menu_favorite)).perform(ViewActions.click())

        Espresso.pressBack()

        // click again test UI
        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                4
            )
        )

        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, ViewActions.click())
        )


        onView(ViewMatchers.withId(iv_team_home)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(iv_team_away)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        // Perfom Click Favorite Match
        onView(ViewMatchers.withId(action_menu_favorite)).perform(ViewActions.click())

        Espresso.pressBack()

        //  Click Menu Next Match
        onView(ViewMatchers.withId(bottom_next_match)).perform(ViewActions.click())
        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                4
            )
        )

        // Click Menu Detail
        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(4, ViewActions.click())
        )

        onView(ViewMatchers.withId(iv_team_home)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(iv_team_away)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(action_menu_favorite)).perform(ViewActions.click())


        Espresso.pressBack()

        //  Click Menu Favorite
        onView(ViewMatchers.withId(bottom_favorite)).perform(ViewActions.click())

        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                0
            )
        )
        onView(ViewMatchers.withId(recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click())
        )

        onView(ViewMatchers.withId(iv_team_home)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(iv_team_away)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        onView(ViewMatchers.withId(action_menu_favorite)).perform(ViewActions.click())

        Espresso.pressBack()

        onView(ViewMatchers.withId(bottom_prev_match)).perform(ViewActions.click())
        onView(ViewMatchers.withId(recyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    /**
     * UI Testing
     * Menu Bottom From (Last Match, Next Match & Favorite)
     * List Item For All Menu
     */
    @Test
    fun menuBottomWithRecyclerViewUITest() {
            // Show Display default menu
            onView(ViewMatchers.withId(recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            // Next Match Menu
            onView(ViewMatchers.withId(bottom_next_match)).perform(ViewActions.click())

            onView(ViewMatchers.withId(recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

            // Favorite Match Menu
            onView(ViewMatchers.withId(bottom_favorite)).perform(ViewActions.click())

            try { // if any item for favorite menu
                onView(ViewMatchers.withId(recyclerView))
                    .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            } catch (e: AssertionFailedError) { // if no item for favorite menu
                onView(ViewMatchers.withId(recyclerView))
                    .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
            }

            // Last Match Menu
            onView(ViewMatchers.withId(bottom_prev_match)).perform(ViewActions.click())

            onView(ViewMatchers.withId(recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }
}
