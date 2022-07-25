package com.serega.timer

import javafx.application.Platform
import javafx.scene.control.TextField
import java.awt.Toolkit
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class TimerTextArea : TextField() {

    private val executor = Executors.newScheduledThreadPool(1)
    private var state = TimerState.RESET
    private var duration: Duration = 6.hours
        set(value) {
            if (value.isNegative()) {
                field = 0.seconds
                Toolkit.getDefaultToolkit().beep();
            } else {
                field = value
            }

            this.lastUpdTime = System.currentTimeMillis()
            this.text = value.toStringCustom()
        }
    private var lastUpdTime: Long = 0L

    init {
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

    fun pauseOrResume() {
        if (state == TimerState.RUNNING) {
            pause()
        } else {
            resume()
        }
    }

    fun pause() {
        if (state != TimerState.RUNNING) {
            return
        }
        state = TimerState.PAUSED
    }

    fun resume() {
        if (state != TimerState.PAUSED) {
            return
        }
        state = TimerState.RUNNING
        lastUpdTime = System.currentTimeMillis()
    }

    fun start() {
        if (state == TimerState.RUNNING) {
            return
        }
        this.duration = Duration.parse(text)

        state = TimerState.RUNNING

        executor.scheduleAtFixedRate(
                {
                    Platform.runLater {
                        if (state == TimerState.RUNNING) {
                            val timePassed = System.currentTimeMillis() - lastUpdTime
                            this.duration = this.duration - timePassed.milliseconds
                        }
                    }
                },
                1,
                1,
                TimeUnit.SECONDS
        )
    }

}