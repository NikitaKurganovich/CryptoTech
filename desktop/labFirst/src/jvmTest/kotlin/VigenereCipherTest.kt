import firstlab.domain.releazation.cipher.VigenereCipher
import kotlin.test.Test

class VigenereCipherTest {
    @Test
    fun `a became h`() {
        TestThat(VigenereCipher("a", "hello").encrypt())
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
