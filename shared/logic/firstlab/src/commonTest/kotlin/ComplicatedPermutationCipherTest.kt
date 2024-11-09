import dev.crypto.base.test.TestThat
import dev.crypto.labfirst.FirstLabErrors
import dev.crypto.labfirst.releazation.cipher.ComplicatedPermutationCipher
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ComplicatedPermutationCipherTest {
    @Test
    fun `a became b`() {
        TestThat(
            ComplicatedPermutationCipher(
                message = "encryption",
                key = Pair(
                    listOf(5, 3, 1, 2, 4),
                    listOf(2, 1)
                )
            ).encrypt()
        )
            .assert(Result.success("niptoycenr"))
    }

    @Test
    fun `not correct length of key 1`() = runBlocking {
        TestThat(
            ComplicatedPermutationCipher(
                message = "encryption",
                key = Pair(
                    listOf(5, 3, 1, 2),
                    listOf(2, 1)
                )
            ).encrypt()
        )
            .assertWithErrorMessage<String>(
                expectedError = FirstLabErrors.KeyFormat.name,
                type = TestThat.ErorrType.IllegalStateException
            )
    }

    @Test
    fun `not correct length of key 2`() = runBlocking {
        TestThat(
            ComplicatedPermutationCipher(
                message = "encryption",
                key = Pair(
                    listOf(5, 3, 1, 2, 5, 4, 5),
                    listOf(2, 1)
                )
            ).encrypt()
        )
            .assertWithErrorMessage<String>(
                expectedError = FirstLabErrors.KeyFormat.name,
                type = TestThat.ErorrType.IllegalStateException
            )
    }
}