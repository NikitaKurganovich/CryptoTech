import dev.crypto.base.resources.ResultMessage
import dev.crypto.base.test.TestThat
import dev.crypto.labfirst.releazation.cipher.VigenereCipher
import org.junit.Test

class VigenereCipherTest {
    @Test
    fun `a became h`() {
        TestThat(VigenereCipher(message = "a", key = "hello").encrypt())
            .assert(Result.success(ResultMessage.StringMessage("h")))
    }

    @Test
    fun `abc became hfn`() {
        TestThat(VigenereCipher("abc", "hello").encrypt())
            .assert(Result.success(ResultMessage.StringMessage("hfn")))
    }

    @Test
    fun `agp became gud`() {
        TestThat(VigenereCipher("agp", "goodbye").encrypt())
            .assert(Result.success(ResultMessage.StringMessage("gud")))
    }

    @Test
    fun `gud became agp`() {
        TestThat(VigenereCipher("gud", "goodbye").decrypt())
            .assert(Result.success(ResultMessage.StringMessage("agp")))
    }

    @Test
    fun `goodbye became goodbye`() {
        TestThat(VigenereCipher("goodbye", "agp").decrypt())
            .assert(Result.success(ResultMessage.StringMessage("gizdvje")))
    }

    @Test
    fun `hello became goodbye`() {
        TestThat(VigenereCipher("hello", "agp").decrypt())
            .assert(Result.success(ResultMessage.StringMessage("hywli")))
    }
}

