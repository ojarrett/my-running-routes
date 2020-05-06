package com.ojarrett.myrunningroutes

class RunIndicatorCollection(private val runIndicators: List<RunIndicator>) {
    private var selected: RunIndicator? = null
    private var started: RunIndicator? = null

    public fun isSelected(): Boolean {
        return selected == null
    }

    public fun setSelected(runIndicator: RunIndicator) {
        if(runIndicator != selected) {
            selected?.getState()?.let { selected?.changeState(it) }
            selected = runIndicator
        }
    }

    // For unit testing purposes only
    fun setStarted(started: RunIndicator) {
        this.started = started
    }

    public fun getRunIndicators(): List<RunIndicator> {
        return runIndicators
    }

    public fun startSelected() {
        selected?.changeState(RunIndicator.RunState.STARTED)
        started = selected
        selected = null
    }

    public fun pauseSelected() {
        if (selected?.getState() == RunIndicator.RunState.STARTED) {
            selected?.changeState(RunIndicator.RunState.PAUSED)
            started = null
            selected = null
        }
    }

    public fun stopSelected() {
        if (selected?.getState() in listOf(RunIndicator.RunState.STARTED, RunIndicator.RunState.PAUSED)) {
            selected?.changeState(RunIndicator.RunState.STOPPED)
            selected = null
            started = null
        }
    }

    public fun resetSelected() {
        // For now, started runs will continue after hitting reset
        if (selected?.getState() == RunIndicator.RunState.STARTED) {
            selected?.changeState(RunIndicator.RunState.STARTED)
        } else {
            selected?.changeState(RunIndicator.RunState.RESET)
        }

        selected = null
    }
}