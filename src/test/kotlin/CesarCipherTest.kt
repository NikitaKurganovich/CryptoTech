import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.format_error
import domain.releazation.CesarCipher
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString
import kotlin.test.Test


class CesarCipherTest {

    @Test
    fun `a became b`() {
        TestThat(CesarCipher("a", 1).encrypt())
            .assert(Result.success("b"))
    }

    @Test
    fun `z became a`() {
        TestThat(CesarCipher("z", 1).encrypt())
            .assert(Result.success("a"))
    }

    @Test
    fun `abc became def`() {
        TestThat(CesarCipher("abc", 3).encrypt())
            .assert(Result.success("def"))
    }

    @Test
    fun `xyz became abc`() {
        TestThat(CesarCipher("xyz", 3).encrypt())
            .assert(Result.success("abc"))
    }


    @Test
    fun `message with spaces`() = runBlocking {
        TestThat(
            CesarCipher("a b c d e f g h i j k l m n o p q r s t u v w x y z", 1)
                .encrypt().toString()
        )
            .assertWithErrorMessage<String>(getString(Res.string.format_error))
    }

    @Test
    fun `message with numbers`() = runBlocking {
        TestThat(CesarCipher("12345", 1).encrypt().toString())
            .assertWithErrorMessage<String>(getString(Res.string.format_error))
    }

    @Test
    fun `message with special characters`() = runBlocking {
        TestThat(CesarCipher("a?b!c", 1).encrypt().toString())
            .assertWithErrorMessage<String>(getString(Res.string.format_error))
    }
}