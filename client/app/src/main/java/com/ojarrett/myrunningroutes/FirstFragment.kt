package com.ojarrett.myrunningroutes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private var runIndicatorCollection = RunController(listOf<RunIndicator>())

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
            GpsTrackManager.instance?.startNewGpsTrack()
            runIndicatorCollection.startSelected()
        }

        view.findViewById<Button>(R.id.button_pause).setOnClickListener {
            GpsTrackManager.instance?.pauseGpsTrack()
            runIndicatorCollection.pauseSelected()
        }

        view.findViewById<Button>(R.id.button_end).setOnClickListener {
            runIndicatorCollection.stopSelected()
        }

        view.findViewById<Button>(R.id.button_reset).setOnClickListener {
            runIndicatorCollection.resetSelected()
        }
    }
}
