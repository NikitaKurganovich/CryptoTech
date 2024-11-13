package dev.crypto.labthird

import io.nacular.measured.units.Time

class TimeWithDays(suffix: String, ratio: Double) : Time(suffix, ratio) {
    companion object {
        val day = TimeWithDays("day", 24 * hours.ratio)
    }
}

