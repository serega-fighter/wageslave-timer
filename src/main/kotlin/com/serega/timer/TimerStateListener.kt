package com.serega.timer

@FunctionalInterface
interface TimerStateListener {

    fun onNewState(state: TimerState)
}