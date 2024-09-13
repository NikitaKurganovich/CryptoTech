import org.junit.Assert.assertEquals

class TestThat<T>(private val valueToTest: T) {
    fun assert(expectedValue: T){
        assertEquals(expectedValue,valueToTest)
    }

    fun <T> assertWithErrorMessage(
        expectedError: String
    ){
        assertEquals(
            errorResult<T>(expectedError).toString(),
            valueToTest.toString()
        )
    }

    private fun <T> errorResult(
        message: String,
    ): Result<T> = runCatching {
        error(message)
    }
}