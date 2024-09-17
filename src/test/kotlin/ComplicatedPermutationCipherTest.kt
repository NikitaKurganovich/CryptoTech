import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.key_warning
import domain.releazation.cipher.ComplicatedPermutationCipher
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString
import kotlin.test.Test

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
            .assertWithErrorMessage<String>(getString(Res.string.key_warning))
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
            .assertWithErrorMessage<String>(getString(Res.string.key_warning))
    }
}