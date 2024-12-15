import dev.crypto.base.resources.ResultMessage
import dev.crypto.base.test.TestThat
import dev.crypto.labfirst.FirstLabErrors
import dev.crypto.labfirst.releazation.cipher.ComplicatedPermutationCipher
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ComplicatedPermutationCipherTest {
    @Test
    fun `encryption became ntpioynecr`() {
        TestThat(
            (ComplicatedPermutationCipher(
                message = "encryption",
                key = Pair(
                    listOf(5, 2, 1, 3, 4),
                    listOf(2, 1)
                )
            ).encrypt().getOrNull() as ResultMessage.IdMessage).args.first()
        )
            .assert("ntpioynecr")
    }

    @Test
    fun `secret became ertesc`() {
        TestThat(
            (ComplicatedPermutationCipher(
                message = "secret",
                key = Pair(
                    listOf(2, 1, 3),
                    listOf(2, 1)
                )
            ).encrypt().getOrNull() as ResultMessage.IdMessage).args.first()
        )
            .assert("ertesc")
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
                expectedError = FirstLabErrors.KeyMultiplication.name,
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
                expectedError = FirstLabErrors.KeyMultiplication.name,
                type = TestThat.ErorrType.IllegalStateException
            )
    }

    @Test
    fun `niptoycenr became encryption`() {
        TestThat(
            (ComplicatedPermutationCipher(
                message = "ntpioynecr",
                key = Pair(
                    listOf(5, 2, 1, 3, 4),
                    listOf(2, 1)
                )
            ).decrypt().getOrNull() as ResultMessage.IdMessage).args.first()
        )
            .assert("encryption")
    }

    @Test
    fun `ertesc became secret`() {
        TestThat(
            (ComplicatedPermutationCipher(
                message = "ertesc",
                key = Pair(
                    listOf(2, 1, 3),
                    listOf(2, 1)
                )
            ).encrypt().getOrNull() as ResultMessage.IdMessage).args.first()
        )
            .assert("secret")
    }
}