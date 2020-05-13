package com.ojarrett.myrunningroutes

import android.location.Location
import android.util.Log

class GpsTrack(val manager: GpsTrackManager): Runnable {
    override fun run() {
        android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND)
        try {
            while (true) {
                val loc: Location? = manager.getLatestLocation()
                if (loc != null) {
                    manager.addPoint(loc)
                    manager.incrementElapsed(3)
                    Log.i(
                        "MainActivity",
                        "Latitude: %f, Longitude: %f".format(loc.latitude, loc.longitude)
                    )
                } else {
                    Log.i("MainActivity", "Location was null!")
                }
                Thread.sleep(3000)
            }
        } catch (e: InterruptedException) {
            return
        }
    }
}