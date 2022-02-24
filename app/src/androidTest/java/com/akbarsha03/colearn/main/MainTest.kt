package com.akbarsha03.colearn.main

import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.akbarsha03.colearn.feature.pics.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainTest {

    private lateinit var loadingIdlingResource: LoadingIdlingResource

    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        loadingIdlingResource = LoadingIdlingResource(rule.activity)
        IdlingRegistry.getInstance().register(loadingIdlingResource)
    }

    @Test
    fun test_if_search_is_showing_results() {
        launchMain {
            clickSearchButton()
            enterQuery("Sky")
            selectSorting("latest")
            selectTone("black_and_white")
            submitSearchQuery()
        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(loadingIdlingResource)
    }
}