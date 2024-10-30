import dev.crypto.first.domain.releazation.key.ComplicatedPermutationCipherKey
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

class ComplicatedPermutationCipherKeyTest {

    @Test
    fun `negative number`() = runBlocking {
        TestThat(ComplicatedPermutationCipherKey("-2 7").formatKey().toString())
            .assertWithErrorMessage<String>("Length of key must be more than 1")
    }

}