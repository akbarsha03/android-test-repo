package com.akbarsha03.colearn.main

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.akbarsha03.colearn.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher

fun launchMain(func: MainRobot.() -> Unit) = MainRobot().apply { func() }

class MainRobot {
    fun submitSearchQuery() {
        val parentMatcher = childAtPosition(withId(R.id.buttonPanel), 0)
        val withText = ViewMatchers.withText("Search")
        val childAtPosition = childAtPosition(parentMatcher, 3)
        val withId = withId(android.R.id.button1)

        val materialButton2 = Espresso.onView(Matchers.allOf(withId, withText, childAtPosition))

        materialButton2.perform(ViewActions.scrollTo(), ViewActions.click())
    }

    fun selectTone(selectTone: String) {

        val matcher = Matchers.`is`("android.widget.HorizontalScrollView")
        val parentMatcher = ViewMatchers.withClassName(matcher)
        val childAtPosition = childAtPosition(parentMatcher, 0)
        val withId = withId(R.id.colorGroup)
        val parentMatcher1 = Matchers.allOf(withId, childAtPosition)
        val childAtPosition1 = childAtPosition(parentMatcher1, 1)
        val withText = ViewMatchers.withText(selectTone)
        val withId1 = withId(R.id.blackAndWhiteColor)

        val containsOf = Matchers.allOf(withId1, withText, childAtPosition1)
        val appCompatRadioButton2 = Espresso.onView(containsOf)

        appCompatRadioButton2.perform(ViewActions.scrollTo(), ViewActions.click())
    }

    fun selectSorting(sortBy: String) {

        val matcher = Matchers.`is`("android.widget.LinearLayout")
        val parentMatcher = ViewMatchers.withClassName(matcher)
        val childAtPosition = childAtPosition(parentMatcher, 2)
        val parentMatcher1 = Matchers.allOf(withId(R.id.sortGroup), childAtPosition)
        val childAtPosition1 = childAtPosition(parentMatcher1, 1)
        val withText = ViewMatchers.withText(sortBy)
        val displayed = ViewMatchers.isDisplayed()
        val withId = withId(R.id.latest)
        val allOf = Matchers.allOf(withId, withText, childAtPosition1, displayed)
        val appCompatRadioButton = Espresso.onView(allOf)

        appCompatRadioButton.perform(ViewActions.click())
    }

    fun enterQuery(query: String) {

        val parentMatcher = withId(R.id.custom)
        val parentMatcher1 = childAtPosition(parentMatcher, 0)
        val displayed = ViewMatchers.isDisplayed()
        val childAtPosition = childAtPosition(parentMatcher1, 0)
        val allOf = Matchers.allOf(withId(R.id.editText), childAtPosition, displayed)

        val appCompatEditText = Espresso.onView(allOf)
        appCompatEditText.perform(ViewActions.replaceText(query), ViewActions.closeSoftKeyboard())
    }

    fun clickSearchButton() {
        Espresso.onView(withId(R.id.searchButton)).perform(ViewActions.click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int,
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