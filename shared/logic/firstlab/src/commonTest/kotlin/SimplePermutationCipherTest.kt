import dev.crypto.base.test.TestThat
import dev.crypto.labfirst.FirstLabErrors
import dev.crypto.labfirst.releazation.cipher.SimplePermutationCipher
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SimplePermutationCipherTest {

    @Test
    fun `simple check`() {
        TestThat(SimplePermutationCipher("abc", listOf(2, 3, 1)).encrypt())
            .assert(Result.success("bca"))
    }

    @Test
    fun `complex check`() {
        TestThat(
            SimplePermutationCipher(
                "encryption",
                listOf(3, 8, 1, 5, 2, 7, 6, 4, 10, 9)
            ).encrypt()
        )
            .assert(Result.success("cieyntprno"))
    }

    @Test
    fun `not correct length of key 1`() = runBlocking {
        TestThat(
            SimplePermutationCipher(
                "encryption",
                listOf(3, 8, 1, 5, 2, 7, 6, 4, 10)
            ).encrypt()
        )
            .assertWithErrorMessage<String>(FirstLabErrors.KeyLength.name)
    }

    @Test
    fun `not correct length of key 2`() = runBlocking {
        TestThat(
            SimplePermutationCipher(
                "encryption",
                listOf(3, 8, 1, 5, 2, 7, 6, 5, 4, 5, 3, 24)
            ).encrypt()
        )
            .assertWithErrorMessage<String>(FirstLabErrors.KeyLength.name)
    }
}