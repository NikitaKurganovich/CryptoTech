import dev.crypto.base.test.TestThat
import dev.crypto.labfirst.releazation.cipher.SimpleReplaceCipher
import org.junit.Test


class SimpleReplaceCipherTest {

    @Test
    fun `a became x`() {
        TestThat(SimpleReplaceCipher("a", "XFQABOLYWJGPMRVIHUSDZKNTEC").encrypt())
            .assert(Result.success("x"))
    }

    @Test
    fun `abc became xfq`() {
        TestThat(SimpleReplaceCipher("abc", "XFQABOLYWJGPMRVIHUSDZKNTEC").encrypt())
            .assert(Result.success("xfq"))
    }

    @Test
    fun `xyz became tec`() {
        TestThat(SimpleReplaceCipher("xyz", "XFQABOLYWJGPMRVIHUSDZKNTEC").encrypt())
            .assert(Result.success("tec"))
    }
}