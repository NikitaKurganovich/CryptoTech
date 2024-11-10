package dev.crypto.labthird

import io.nacular.measured.units.Time
import java.math.BigDecimal

interface PasswordStrengthRequirements {
    val probability: BigDecimal
    val bruteForceSpeed: BruteForceSpeed
    val timeToBruteForce: Time
}