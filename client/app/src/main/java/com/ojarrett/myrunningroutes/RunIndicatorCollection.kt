package com.ojarrett.myrunningroutes

class RunIndicatorCollection(private val runIndicators: List<RunIndicator>) {
    private var selected: RunIndicator? = null

    public fun isSelected(): Boolean {
        return selected == null
    }

    public fun setSelected(runIndicator: RunIndicator) {
        if(runIndicator != selected) {
            selected?.changeState(RunIndicator.RunState.RESET)
            selected = runIndicator
        }
    }

    public fun getRunIndicators(): List<RunIndicator> {
        return runIndicators
    }
}