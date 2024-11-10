package dev.crypto.labthird

import io.nacular.measured.units.Time

val Time.days: Time
    get() = Time("days",  24 * Time.hours.ratio)

