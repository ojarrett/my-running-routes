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
    val fakeImageViewHandler = ImageViewHandler()
    var runIndicator = RunIndicator(fakeImageViewHandler)
    var otherRunIndicator1 = RunIndicator(fakeImageViewHandler)
    var otherRunIndicator2 = RunIndicator(fakeImageViewHandler)
    var runIndicatorCollection = RunIndicatorCollection(
        listOf(runIndicator, otherRunIndicator1, otherRunIndicator2))

    private fun attachRunIndicatorsToCollection(selected: RunIndicator?, started: RunIndicator?) {
        for(ri in runIndicatorCollection.getRunIndicators()) {
            ri.setCollection(runIndicatorCollection)
        }

        if (selected != null) {
            runIndicatorCollection.setSelected(selected)
        }

        if (started != null) {
            runIndicatorCollection.setStarted(started)
        }
    }

    @Before
    fun setup() {
        runIndicator = RunIndicator(fakeImageViewHandler)
        otherRunIndicator1 = RunIndicator(fakeImageViewHandler)
        otherRunIndicator2 = RunIndicator(fakeImageViewHandler)
        val runIndicators = listOf(runIndicator, otherRunIndicator1, otherRunIndicator2)
        runIndicatorCollection = RunIndicatorCollection(runIndicators)
    }

    @Test
    fun runIndicatorInitToReset() {
        assertEquals(RunIndicator.RunState.RESET, runIndicator.getState())
    }

    @Test
    fun runIndicatorToSelectedNoCollection() {
        runIndicator.changeState(RunIndicator.RunState.SELECTED)
        otherRunIndicator1.changeState(RunIndicator.RunState.SELECTED)
        assertEquals(RunIndicator.RunState.SELECTED, runIndicator.getState())
        assertEquals(RunIndicator.RunState.SELECTED, otherRunIndicator1.getState())
    }

    @Test
    fun runIndicatorToSelectedWithCollection() {
        attachRunIndicatorsToCollection(null, null)

        runIndicator.changeState(RunIndicator.RunState.SELECTED)
        otherRunIndicator1.changeState(RunIndicator.RunState.SELECTED)
        assertEquals(RunIndicator.RunState.RESET, runIndicator.getState())
        assertEquals(RunIndicator.RunState.SELECTED, otherRunIndicator1.getState())
    }

    @Test
    fun runIndicatorToSelectedTwiceWithCollection() {
        attachRunIndicatorsToCollection(runIndicator, null)

        runIndicator.changeState(RunIndicator.RunState.SELECTED)
        assertEquals(RunIndicator.RunState.SELECTED, runIndicator.getState())
    }

    @Test
    fun startSelectedRunIndicator() {
        attachRunIndicatorsToCollection(runIndicator, null)

        runIndicatorCollection.startSelected()
        assertEquals(RunIndicator.RunState.STARTED, runIndicator.getState())
    }

    @Test
    fun pauseStartedRunIndicator() {
        attachRunIndicatorsToCollection(null, runIndicator)

        runIndicatorCollection.pauseStarted()
        assertEquals(RunIndicator.RunState.PAUSED, runIndicator.getState())
    }
}