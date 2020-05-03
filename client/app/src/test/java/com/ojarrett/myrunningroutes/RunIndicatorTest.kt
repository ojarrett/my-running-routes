package com.ojarrett.myrunningroutes

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RunIndicatorTest {
    val imageViewHandler = ImageViewHandler()
    var runIndicator = RunIndicator(imageViewHandler)

    @Before
    fun setup() {
        runIndicator = RunIndicator(imageViewHandler)
    }

    @Test
    fun runIndicatorInitToReset() {
        assertEquals(RunIndicator.RunState.RESET, runIndicator.getState())
    }

    @Test
    fun runIndicatorToSelected() {
        runIndicator.changeState(RunIndicator.RunState.SELECTED)
        assertEquals(RunIndicator.RunState.SELECTED, runIndicator.getState())
    }
}