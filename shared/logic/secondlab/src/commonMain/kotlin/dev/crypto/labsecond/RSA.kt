package dev.crypto.labsecond

import dev.crypto.base.interfaces.Cipher
import kotlin.math.pow

data class RSA(
    override val message: String,
    override val key: IntKeyTriplet
) : Cipher<IntKeyTriplet> {
    private val n = key.p * key.q
    private val phi = (key.p - 1) * (key.q - 1)

    override fun encrypt(): Result<String> = runWithInitCheck {
        message.map { char ->
            val i = char.lowercaseChar() - 'a' + 1
            i.toBigDecimal().pow(key.e) % n.toBigDecimal()
        }.joinToString(separator = " ")
    }

    override fun decrypt(): Result<String> = runWithInitCheck {
        val d = generateSequence(1) { it + 1 }.first { (it * key.e) % phi == 1 }
        message.split(" ")
            .map { num ->
                val i = num.toInt()
                i.toBigDecimal().pow(d) % n.toBigDecimal()
            }
            .map { it.toInt() }
            .map { (it - 1 + 'a'.code).toChar() }.joinToString("")
    }

    private fun runWithInitCheck(
        block: () -> String
    ): Result<String> = runCatching {
        require(isPrime(key.p) && isPrime(key.q)) {
            SecondLabErrors.QAndPNotPrime
        }
        require(
            gcd(
                key.e,
                (key.p - 1) * (key.q - 1)
            ) == 1
        ) {
            SecondLabErrors.ENotCoprime
        }
        require(key.e > 1 && key.e < (key.p - 1) * (key.q - 1)) {
            SecondLabErrors.EGreaterThanPhi
        }
        require(key.p * key.q > 26) {
            SecondLabErrors.LowQAndPProduct
        }
        block()
    }

    private fun isPrime(num: Int): Boolean {
        if (num <= 1) return false
        for (i in 2..num / 2) {
            if (num % i == 0) return false
        }
        return true
    }

    private fun gcd(a: Int, b: Int): Int {
        return if (b == 0) a else gcd(b, a % b)
    }
}