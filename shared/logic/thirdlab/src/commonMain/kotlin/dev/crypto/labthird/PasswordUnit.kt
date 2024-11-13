package dev.crypto.labthird

import io.nacular.measured.units.Units

class PasswordUnit(
    suffix: String = "password",
    ratio: Double = 1.0
) : Units(suffix, ratio){
    companion object{
        val password = PasswordUnit()
    }
}