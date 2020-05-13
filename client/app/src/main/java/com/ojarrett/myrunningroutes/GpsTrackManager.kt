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
    private var gpsThreads: MutableList<Thread> = mutableListOf()
    private var currentThread: Int = 0
    private var points: MutableList<Location> = mutableListOf()

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
        val gpsThread = Thread(GpsTrack(this))
        currentThread = gpsThreads.size
        gpsThread.start()
        gpsThreads.add(gpsThread)
    }

    public fun pauseGpsTrack() {
        gpsThreads[currentThread].interrupt()
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
}