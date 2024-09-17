import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.numbers_warning
import domain.releazation.key.SimplePermutationCipherKey
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString
import org.junit.Test

class SimplePermutationCipherKeyTest {
    @Test
    fun `positive number`() {
        TestThat(SimplePermutationCipherKey("6").formatKey().getOrNull()!!.isNotEmpty())
            .assert(true)
    }

    @Test
    fun `negative number`() = runBlocking {
        TestThat(SimplePermutationCipherKey("-2").formatKey().toString())
            .assertWithErrorMessage<String>("Length of key must more than 1")
    }

    @Test
    fun `letter`() = runBlocking {
        TestThat(SimplePermutationCipherKey("d").formatKey().toString())
            .assertWithErrorMessage<String>(getString(Res.string.numbers_warning))
    }

    @Test
    fun `special char`() = runBlocking {
        TestThat(SimplePermutationCipherKey("&").formatKey().toString())
            .assertWithErrorMessage<String>(getString(Res.string.numbers_warning))
    }

    @Test
    fun `empty`() = runBlocking {
        TestThat(SimplePermutationCipherKey("").formatKey().toString())
            .assertWithErrorMessage<String>(getString(Res.string.numbers_warning))
    }

    @Test
    fun `space`() = runBlocking {
        TestThat(SimplePermutationCipherKey(" ").formatKey().toString())
            .assertWithErrorMessage<String>(getString(Res.string.numbers_warning))
    }

    @Test
    fun `1`() = runBlocking {
        TestThat(SimplePermutationCipherKey("1").formatKey().toString())
            .assertWithErrorMessage<String>("Length of key must be more than 1")
    }
}