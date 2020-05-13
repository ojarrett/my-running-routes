package com.ojarrett.myrunningroutes

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val gpsTracker: GpsTrackManager = GpsTrackManager()

    enum class MyPermissions(val code: Int) {
        ACCESS_COARSE_LOCATION(100),
        ACCESS_BACKGROUND_LOCATION(101)
    }

    private fun bindNewLocationProvider() {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        gpsTracker.setLocationProvider(fusedLocationClient)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                MyPermissions.ACCESS_COARSE_LOCATION.code
            )
        } else {
            bindNewLocationProvider()
        }

        gpsTracker.startNewGpsTrack()

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MyPermissions.ACCESS_COARSE_LOCATION.code -> {
                bindNewLocationProvider()
            }
            MyPermissions.ACCESS_BACKGROUND_LOCATION.code -> {
                // TODO: Fill this in when background run logging is added
            }
            else -> {
                // Nothing do to do here for now
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
