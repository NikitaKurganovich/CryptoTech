import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import domain.releazation.VigenereCipherKey
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString
import org.junit.Test

class VigenereCipherKeyTest {
    @Test
    fun `letters`() {
        TestThat(VigenereCipherKey("hello").formatKey().getOrNull()!!.isNotEmpty())
            .assert(true)
    }

    @Test
    fun `empty`() = runBlocking {
        TestThat(VigenereCipherKey("").formatKey().toString())
            .assertWithErrorMessage<String>(getString(Res.string.format_error))
    }

    @Test
    fun `spaces`() = runBlocking {
        TestThat(VigenereCipherKey(" ").formatKey().toString())
            .assertWithErrorMessage<String>(getString(Res.string.format_error))
    }

    @Test
    fun `number`() = runBlocking {
        TestThat(VigenereCipherKey("12").formatKey().toString())
            .assertWithErrorMessage<String>(getString(Res.string.format_error))
    }

    @Test
    fun `special char`() = runBlocking {
        TestThat(VigenereCipherKey("%").formatKey().toString())
            .assertWithErrorMessage<String>(getString(Res.string.format_error))
    }
}