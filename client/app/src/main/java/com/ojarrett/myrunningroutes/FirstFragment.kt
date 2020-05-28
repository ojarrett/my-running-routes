package com.ojarrett.myrunningroutes

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private var runIndicatorCollection = RunController(listOf<RunIndicator>())

    class FragmentUpdateTask(val handler: Handler): TimerTask() {
        override fun run() {
            handler.sendMessage(Message())
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var runIndicators = mutableListOf<RunIndicator>()

        for (circle in listOf(R.id.circle_run_1, R.id.circle_run_2, R.id.circle_run_3)) {
            val imgView = view.findViewById<ImageView>(circle)
            val imageViewHandler = ImageViewHandler()
            imageViewHandler.setImageView(imgView)
            val runIndicator = RunIndicator(imageViewHandler)
            runIndicators.add(runIndicator)

            imgView.setOnClickListener {
                runIndicator.changeState(RunIndicator.RunState.SELECTED)
            }
        }

        runIndicatorCollection = RunController(runIndicators)
        for (runIndicator in runIndicators) {
            runIndicator.setCollection(runIndicatorCollection)
        }


        view.findViewById<Button>(R.id.button_start).setOnClickListener {
            if (runIndicatorCollection.isSelected()) {
                GpsTrackManager.instance?.startNewGpsTrack(runIndicatorCollection.getSelectedIndex())
            }
            runIndicatorCollection.startSelected()
        }

        view.findViewById<Button>(R.id.button_pause).setOnClickListener {
            if (runIndicatorCollection.isSelected()) {
                GpsTrackManager.instance?.pauseGpsTrack(runIndicatorCollection.getSelectedIndex())
            }
            runIndicatorCollection.pauseSelected()
        }

        view.findViewById<Button>(R.id.button_end).setOnClickListener {
            runIndicatorCollection.stopSelected()
        }

        view.findViewById<Button>(R.id.button_reset).setOnClickListener {
            runIndicatorCollection.resetSelected()
        }

        val timer: Timer = Timer()
        val handler: Handler = object : Handler() {
            override fun handleMessage(m: Message): Unit {
                val gps = GpsTrackManager.instance
                when(gps) {
                    null -> {}
                    else -> {
                        val points = gps.getPoints().size
                        val elapsed = gps.getElapsed()
                        val statusString = "Elapsed time: %d seconds. Points collected: %d".format(elapsed, points)
                        val text: TextView = view.findViewById(R.id.gps_stats_textview)
                        text.setText(statusString)
                        text.visibility = VISIBLE
                    }
                }
            }
        }
        timer.schedule(FragmentUpdateTask(handler), 0, 3000)
    }
}
