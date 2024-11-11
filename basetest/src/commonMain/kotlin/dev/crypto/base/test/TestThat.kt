package dev.crypto.base.test

import kotlin.runCatching
import kotlin.test.assertEquals
import kotlin.toString

class TestThat<T>(private val valueToTest: T) {
    fun assert(expectedValue: T) {
        assertEquals(expectedValue, valueToTest)
    }

    fun <T> assertWithErrorMessage(
        expectedError: String,
        type: ErorrType = ErorrType.IllegalArgumentException
    ) {
        when (type) {
            ErorrType.IllegalArgumentException -> assertEquals(
                illegalArgumentResult<T>(expectedError).toString(),
                valueToTest.toString()
            )

            ErorrType.IllegalStateException -> assertEquals(
                illegalStateResult<T>(expectedError).toString(),
                valueToTest.toString()
            )
        }
    }

    fun assertNotNull() {
        assert(valueToTest != null)
    }

    private fun <T> illegalStateResult(
        message: String,
    ): Result<T> = runCatching {
        error(message)
    }

    private fun <T> illegalArgumentResult(
        message: String,
    ): Result<T> = runCatching {
        throw IllegalArgumentException(message)
    }

    enum class ErorrType {
        IllegalArgumentException,
        IllegalStateException
    }
}