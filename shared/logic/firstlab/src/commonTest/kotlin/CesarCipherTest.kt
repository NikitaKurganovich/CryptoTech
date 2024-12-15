import dev.crypto.base.resources.ResultMessage
import dev.crypto.base.test.TestThat
import dev.crypto.labfirst.FirstLabErrors
import dev.crypto.labfirst.releazation.cipher.CesarCipher
import kotlinx.coroutines.runBlocking
import org.junit.Test


class CesarCipherTest {

    @Test
    fun `a became b`() {
        TestThat(CesarCipher("a", 1).encrypt())
            .assert(Result.success(ResultMessage.StringMessage("b")))
    }

    @Test
    fun `z became a`() {
        TestThat(CesarCipher("z", 1).encrypt())
            .assert(Result.success(ResultMessage.StringMessage("a")))
    }

    @Test
    fun `abc became def`() {
        TestThat(CesarCipher("abc", 3).encrypt())
            .assert(Result.success(ResultMessage.StringMessage("def")))
    }

    @Test
    fun `xyz became abc`() {
        TestThat(CesarCipher("xyz", 3).encrypt())
            .assert(Result.success(ResultMessage.StringMessage("abc")))
    }

    @Test
    fun `message with spaces`() = runBlocking {
        TestThat(
            CesarCipher("a b c d e f g h i j k l m n o p q r s t u v w x y z", 1)
                .encrypt().toString()
        )
            .assertWithErrorMessage<String>(FirstLabErrors.MessageFormat.name)
    }

    @Test
    fun `message with numbers`() = runBlocking {
        TestThat(CesarCipher("12345", 1).encrypt().toString())
            .assertWithErrorMessage<String>(FirstLabErrors.MessageFormat.name)
    }

    @Test
    fun `message with special characters`() = runBlocking {
        TestThat(CesarCipher("a?b!c", 1).encrypt().toString())
            .assertWithErrorMessage<String>(FirstLabErrors.MessageFormat.name)
    }
}