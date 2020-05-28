package com.ojarrett.myrunningroutes

import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient

class GpsTrackManager(val numTracks: Int) {

    companion object {
        var instance:GpsTrackManager? = null
    }

    private var provider: FusedLocationProviderClient? = null
    private var lastLocation: Location? = null
    private var gpsThreads: MutableMap<Int, Thread> = mutableMapOf()
    private var points: MutableList<MutableList<Location>> = mutableListOf()
    private var elapsed: MutableList<Int> = mutableListOf()

    init {
        // TODO: Fix if this is done more than once
        instance = this
        for(indicator in 0..numTracks) {
            points.add(mutableListOf())
            elapsed.add(0)
        }
    }

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
            val gpsThread = Thread(GpsTrack(this, index))
            gpsThread.start()
            gpsThreads[index] = gpsThread
        }
    }

    public fun pauseGpsTrack(index: Int) {
        if (gpsThreads.keys.contains(index)) {
            gpsThreads[index]?.interrupt()
            gpsThreads.remove(index)
        }
    }

    public fun addPoint(location: Location, trackIndex: Int) {
        points[trackIndex].add(location)
    }

    public fun getPoints(track: Int): List<Location> {
        return points[track]
    }

    public fun resetPoints() {
        points = mutableListOf()
    }

    public fun incrementElapsed(by: Int, trackIndex: Int) {
        elapsed[trackIndex] += by
    }

    fun getElapsed(trackIndex: Int): Int {
        return elapsed[trackIndex]
    }
}