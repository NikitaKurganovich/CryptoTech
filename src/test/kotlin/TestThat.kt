import kotlin.test.assertEquals

class TestThat<T>(private val valueToTest: T) {
    fun assert(expectedValue: T){
        assertEquals(
            expected = expectedValue,
            actual = valueToTest
        )
    }

    fun <T> assertWithErrorMessage(
        expectedError: String
    ){
        assertEquals(
            expected = errorResult<T>(expectedError).toString(),
            actual = valueToTest.toString()
        )
    }

    private fun <T> errorResult(
        message: String,
    ): Result<T> = runCatching {
        error(message)
    }
}