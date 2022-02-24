package com.akbarsha03.colearn

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
open class BaseUnitTest {

    // For the coroutine scoping
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    // For execution of live data that happen instantly, so we can use the values in the test
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}