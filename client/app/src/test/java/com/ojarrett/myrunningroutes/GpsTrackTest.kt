package com.ojarrett.myrunningroutes

import android.location.Location
import org.junit.Assert.assertEquals
import org.junit.Test

class GpsTrackTest {

    @Test
    public fun gpsTrackManagerInit() {
        val gpsTrackManager = GpsTrackManager(4)
        gpsTrackManager.setLatestLocation(Location("test"))
        gpsTrackManager.startNewGpsTrack(0)

        // TODO: Mock sleeps
        Thread.sleep(6000)
        assertEquals(2, gpsTrackManager.getPoints(0).size)
    }
}