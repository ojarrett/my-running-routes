package com.ojarrett.myrunningroutes

import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient

class GpsTrackManager {

    companion object {
        var instance:GpsTrackManager? = null
    }

    init {
        // TODO: Fix if this is done more than once
        instance = this
    }
    private var provider: FusedLocationProviderClient? = null
    private var lastLocation: Location? = null
    private var gpsThreads: MutableMap<Int, Thread> = mutableMapOf()
    private var points: MutableList<Location> = mutableListOf()
    private var elapsed: Int = 0

    public fun setLocationProvider(locationProvider:  FusedLocationProviderClient) {
        this.provider = locationProvider
    }

    public fun getLatestLocation(): Location? {
        provider?.lastLocation?.addOnSuccessListener {
                location: Location? -> this.lastLocation = location
        }

        return lastLocation
    }

    public fun startNewGpsTrack(index: Int) {
        if (!gpsThreads.keys.contains(index)) {
            val gpsThread = Thread(GpsTrack(this))
            gpsThread.start()
            gpsThreads[index] = gpsThread
        }
    }

    public fun pauseGpsTrack(index: Int) {
        if (gpsThreads.keys.contains(index)) {
            gpsThreads[index]?.interrupt()
        }
    }

    public fun addPoint(location: Location) {
        points.add(location)
    }

    public fun getPoints(): List<Location> {
        return points
    }

    public fun resetPoints() {
        points = mutableListOf()
    }

    public fun incrementElapsed(by: Int) {
        elapsed += by
    }

    fun getElapsed(): Int {
        return elapsed
    }
}