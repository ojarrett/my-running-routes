package com.ojarrett.myrunningroutes

import android.location.Location
import org.junit.Assert.assertEquals
import org.junit.Test

class GpsTrackTest {

    @Test
    public fun gpsTrackManagerInit() {
        val gpsTrackManager = GpsTrackManager(4)
        var pollingCount = 0
        gpsTrackManager.setPollingFunction {
            if (pollingCount == 3) {
                Thread.sleep(300000)
            } else {
                pollingCount++
            }
        }
        gpsTrackManager.setLatestLocation(Location("test"))
        gpsTrackManager.startNewGpsTrack(0)

        while(pollingCount < 3) {Thread.sleep(10)}
        assertEquals(4, gpsTrackManager.getPoints(0).size)
    }
}