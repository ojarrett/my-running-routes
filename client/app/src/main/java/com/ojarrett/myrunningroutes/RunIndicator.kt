package com.ojarrett.myrunningroutes


class RunIndicator(val view: ImageViewHandler) {
    enum class RunState {RESET, SELECTED, STARTED, PAUSED, STOPPED}
    private var currentState = RunState.RESET
    private var isSelected = false
    private var collection: RunController? = null

    public fun changeState(nextState: RunState) {

        var nextSrcImage = when(nextState) {
            RunState.RESET -> R.drawable.run_selector
            // Selected is a pseudo-state, tracked separately as isSelected to preserve
            // previous state
            RunState.SELECTED -> R.drawable.run_selector_white
            RunState.PAUSED -> R.drawable.run_selector_orange
            RunState.STOPPED -> R.drawable.run_selector_red
            RunState.STARTED -> R.drawable.run_selector_green
        }

        view.changeImage(nextSrcImage)
        if(nextState == RunState.SELECTED) {
            collection?.setSelected(this)
            this.isSelected = true
        } else {
            currentState = nextState
            this.isSelected = false
        }
    }

    public fun getState(): RunState {
        return currentState
    }

    public fun isSelected(): Boolean {
        return isSelected
    }

    public fun setCollection(collection: RunController) {
        this.collection = collection
    }

    init {
        this.changeState(RunState.RESET)
    }
}