package com.serega.timer

import javafx.scene.control.TextField
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours

class TimerTextArea : TextField() {

    fun Duration.toStringCustom(): String {
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

    var duration: Duration = 6.hours
        set(value) {
            field = value
            this.text = value.toStringCustom()
        }

}