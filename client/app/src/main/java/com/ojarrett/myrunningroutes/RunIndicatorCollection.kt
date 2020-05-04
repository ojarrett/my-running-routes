package com.ojarrett.myrunningroutes

class RunIndicatorCollection(private val runIndicators: List<RunIndicator>) {
    private var selected: RunIndicator? = null
    private var started: RunIndicator? = null

    public fun isSelected(): Boolean {
        return selected == null
    }

    public fun setSelected(runIndicator: RunIndicator) {
        if(runIndicator != selected) {
            selected?.changeState(RunIndicator.RunState.RESET)
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

    public fun pauseStarted() {
        started?.changeState(RunIndicator.RunState.PAUSED)
    }

    public fun stop() {
        started?.changeState(RunIndicator.RunState.STOPPED)
        started = null
    }

    public fun resetSelected() {
        selected?.changeState(RunIndicator.RunState.RESET)
        selected = null
    }
}