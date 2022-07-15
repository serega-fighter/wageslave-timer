package com.serega.timer

import javafx.application.Platform
import javafx.scene.control.TextField
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds

class TimerTextArea : TextField() {

    private val executor = Executors.newScheduledThreadPool(1)
    private var startTime = System.currentTimeMillis()
    private var state = TimerState.RESET
    private var timeInitialized = 6.hours
    private var timeLeft = 6.hours
        set(value) {
            field = value
            this.text = value.toStringCustom()
        }


    init {
        timeLeft = timeInitialized

        setOnAction {
            start()
        }
    }

    private fun Duration.toStringCustom(): String {
        var text = ""
        val hours = this.inWholeHours
        if (hours > 0) {
            text += "$hours hrs "
        }
        val minutesOfHour = this.inWholeMinutes - hours * 60
        text += "$minutesOfHour mins "
        val secondsOfMinutes = this.inWholeSeconds - (hours * 3600 + minutesOfHour * 60)
        text += " $secondsOfMinutes sec"

        return text
    }

    fun start() {
        startTime = System.currentTimeMillis()
        state = TimerState.RUNNING

        executor.scheduleAtFixedRate(
                {
                    Platform.runLater {
                        val timePassed = System.currentTimeMillis() - startTime
                        this.timeLeft = this.timeInitialized - timePassed.milliseconds
                    }
                },
                1,
                1,
                TimeUnit.SECONDS
        )
    }

}