package com.ojarrett.myrunningroutes

import android.graphics.drawable.Drawable
import android.widget.ImageView

class RunIndicator(val view: ImageViewHandler) {
    enum class RunState {RESET, SELECTED, STARTED, PAUSED, STOPPED}
    private var currentState = RunState.RESET

    public fun changeState(nextState: RunState) {

        var nextSrcImage = when(nextState) {
            RunState.RESET -> R.drawable.run_selector
            RunState.SELECTED -> R.drawable.run_selector_white
            RunState.PAUSED -> R.drawable.run_selector_orange
            RunState.STOPPED -> R.drawable.run_selector_red
            RunState.STARTED -> R.drawable.run_selector_green
        }

        view.changeImage(nextSrcImage)
        currentState = nextState
    }

    init {
        this.changeState(RunState.RESET)
    }
}