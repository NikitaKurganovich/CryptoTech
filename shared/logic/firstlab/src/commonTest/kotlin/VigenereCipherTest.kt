import dev.crypto.base.test.TestThat
import dev.crypto.labfirst.releazation.cipher.VigenereCipher
import org.junit.Test

class VigenereCipherTest {
    @Test
    fun `a became h`() {
        TestThat(VigenereCipher(message = "a", key = "hello").encrypt())
            .assert(Result.success("h"))
    }

    @Test
    fun `abc became hfn`() {
        TestThat(VigenereCipher("abc", "hello").encrypt())
            .assert(Result.success("hfn"))
    }

    @Test
    fun `agp became gud`() {
        TestThat(VigenereCipher("agp", "goodbye").encrypt())
            .assert(Result.success("gud"))
    }
}
