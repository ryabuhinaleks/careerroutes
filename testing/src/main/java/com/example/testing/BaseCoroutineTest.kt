package com.example.testing

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseCoroutineTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    protected val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    open fun setUp() {
        Dispatchers.setMain(testDispatcher)
        onSetUp()
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
        onTearDown()
    }

    abstract fun onSetUp()
    abstract fun onTearDown()
}