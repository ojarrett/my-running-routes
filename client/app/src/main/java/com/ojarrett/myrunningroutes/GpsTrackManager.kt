package com.ojarrett.myrunningroutes

import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient

class GpsTrackManager {
    private var provider: FusedLocationProviderClient? = null
    private var lastLocation: Location? = null

    public fun setLocationProvider(locationProvider:  FusedLocationProviderClient) {
        this.provider = locationProvider
    }

    public fun getLatestLocation(): Location? {
        var lastLocation: Location? = null
        provider?.lastLocation?.addOnSuccessListener {
                location: Location? -> lastLocation = location
        }

        return lastLocation
    }
}