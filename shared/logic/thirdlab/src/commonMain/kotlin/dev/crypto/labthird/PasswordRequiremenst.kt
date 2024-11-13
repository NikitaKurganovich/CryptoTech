package dev.crypto.labthird

import dev.crypto.labthird.PasswordUnit.Companion.password
import dev.crypto.labthird.TimeWithDays.Companion.day
import io.nacular.measured.units.Measure
import io.nacular.measured.units.Time
import io.nacular.measured.units.div
import io.nacular.measured.units.times
import java.math.BigDecimal

val androidPasswordRequirements = object : PasswordStrengthRequirements {
        override val probability: BigDecimal
            get() = BigDecimal.valueOf(1, 7)
        override val bruteForceSpeed: BruteForceSpeed
            get() = 100 * password / day
        override val timeToBruteForce: Measure<Time>
            get() = 10 * day
    }

val desktopPasswordRequirements = object : PasswordStrengthRequirements {
        override val probability: BigDecimal
            get() = BigDecimal.valueOf(1, 7)
        override val bruteForceSpeed: BruteForceSpeed
            get() = 100 * password / day
        override val timeToBruteForce: Measure<Time>
            get() = 10 * day
    }