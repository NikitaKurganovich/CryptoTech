import dev.crypto.base.resources.ResultMessage
import dev.crypto.base.test.TestThat
import dev.crypto.labfirst.FirstLabErrors
import dev.crypto.labfirst.releazation.cipher.SimplePermutationCipher
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SimplePermutationCipherTest {

    @Test
    fun `simple check`() {
        TestThat(
            (SimplePermutationCipher("abc", listOf(2, 3, 1)).encrypt()
                .getOrNull() as ResultMessage.IdMessage)
                .args.first()
        )
            .assert("bca")
    }

    @Test
    fun `complex check`() {
        TestThat(
            (SimplePermutationCipher(
                "encryption",
                listOf(3, 8, 1, 5, 2, 7, 6, 4, 10, 9)
            ).encrypt().getOrNull() as ResultMessage.IdMessage)
                .args.first()
        )
            .assert("cieyntprno")
    }

    @Test
    fun `not correct length of key 1`() = runBlocking {
        TestThat(
            SimplePermutationCipher(
                "encryption",
                listOf(3, 8, 1, 5, 2, 7, 6, 4, 10)
            ).encrypt()
        )
            .assertWithErrorMessage<String>(
                expectedError = FirstLabErrors.KeyLength.name,
                type = TestThat.ErorrType.IllegalStateException
            )
    }

    @Test
    fun `not correct length of key 2`() = runBlocking {
        TestThat(
            SimplePermutationCipher(
                "encryption",
                listOf(3, 8, 1, 5, 2, 7, 6, 5, 4, 5, 3, 24)
            ).encrypt()
        )
            .assertWithErrorMessage<String>(
                expectedError = FirstLabErrors.KeyLength.name,
                type = TestThat.ErorrType.IllegalStateException
            )
    }

    @Test
    fun `bca become abc`() {
        TestThat(
            (SimplePermutationCipher("bca", listOf(2, 3, 1)).decrypt()
                .getOrNull() as ResultMessage.IdMessage)
                .args.first()
        )
            .assert("abc")
    }

    @Test
    fun `cieyntprno become encryption`() {
        TestThat(
            (SimplePermutationCipher(
                "cieyntprno",
                listOf(3, 8, 1, 5, 2, 7, 6, 4, 10, 9)
            ).decrypt().getOrNull() as ResultMessage.IdMessage)
                .args.first()
        )
            .assert("encryption")
    }
}