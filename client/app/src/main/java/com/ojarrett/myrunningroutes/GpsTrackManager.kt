package com.ojarrett.myrunningroutes

import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient

class GpsTrackManager {
    class GpsTrack(val manager: GpsTrackManager): Runnable {
        override fun run() {
            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND)
            while(true) {
                val loc: Location? = manager.getLatestLocation()
                if (loc != null) {
                    Log.i(
                        "MainActivity",
                        "Latitude: %f, Longitude: %f".format(loc.latitude, loc.longitude)
                    )
                } else {
                    Log.i("MainActivity", "Location was null!")
                }
                Thread.sleep(3000)
            }
        }
    }
    private var provider: FusedLocationProviderClient? = null
    private var lastLocation: Location? = null
    private val gpsTrack: GpsTrack = GpsTrack(this)

    public fun setLocationProvider(locationProvider:  FusedLocationProviderClient) {
        this.provider = locationProvider
    }

    public fun getLatestLocation(): Location? {
        provider?.lastLocation?.addOnSuccessListener {
                location: Location? -> this.lastLocation = location
        }

        return lastLocation
    }

    public fun startNewGpsTrack() {
        Thread(gpsTrack).start()
    }
}