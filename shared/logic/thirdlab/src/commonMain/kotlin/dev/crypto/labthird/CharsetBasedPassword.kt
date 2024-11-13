package dev.crypto.labthird

import java.security.SecureRandom

class CharsetBasedPassword(
    private val charset: List<Char>,
    private val requiredLength: Int
) {
    init {
        require(charset.isNotEmpty()) { ThirdLabErrorResults.CharsetEmpty }
        require(requiredLength > 0) { ThirdLabErrorResults.PasswordLengthTooShort }
    }

    fun generatePassword(): String {
        val secureRandom = SecureRandom()
        return (1..requiredLength)
            .map { charset[secureRandom.nextInt(charset.size)] }
            .joinToString("")
    }
}