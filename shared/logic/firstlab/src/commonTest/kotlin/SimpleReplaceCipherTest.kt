import dev.crypto.base.resources.ResultMessage
import dev.crypto.base.test.TestThat
import dev.crypto.labfirst.releazation.cipher.SimpleReplaceCipher
import org.junit.Test
import java.util.Locale

class SimpleReplaceCipherTest {

    @Test
    fun `a became x`() {
        TestThat(
            (SimpleReplaceCipher(
                "a",
                "XFQABOLYWJGPMRVIHUSDZKNTEC".lowercase(Locale.getDefault())
            ).encrypt()
                .getOrNull() as ResultMessage.IdMessage)
                .args.first()
        )
            .assert("x")
    }

    @Test
    fun `abc became xfq`() {
        TestThat(
            (SimpleReplaceCipher(
                "abc",
                "XFQABOLYWJGPMRVIHUSDZKNTEC".lowercase(Locale.getDefault())
            ).encrypt()
                .getOrNull() as ResultMessage.IdMessage)
                .args.first()
        )
            .assert("xfq")
    }

    @Test
    fun `xyz became tec`() {
        TestThat(
            (SimpleReplaceCipher(
                "xyz",
                "XFQABOLYWJGPMRVIHUSDZKNTEC".lowercase(Locale.getDefault())
            ).encrypt()
                .getOrNull() as ResultMessage.IdMessage)
                .args.first()
        )
            .assert("tec")
    }

    @Test
    fun `secret became sbqubd`() {
        TestThat(
            (SimpleReplaceCipher(
                "secret",
                "XFQABOLYWJGPMRVIHUSDZKNTEC".lowercase(Locale.getDefault())
            ).encrypt()
                .getOrNull() as ResultMessage.IdMessage)
                .args.first()
        )
            .assert("sbqubd")
    }

    @Test
    fun `sbqubd became secret`() {
        TestThat(
            (SimpleReplaceCipher(
                "sbqubd",
                "XFQABOLYWJGPMRVIHUSDZKNTEC".lowercase(Locale.getDefault())
            ).decrypt()
                .getOrNull() as ResultMessage.IdMessage)
                .args.first()
        )
            .assert("secret")
    }

    @Test
    fun `tec became xyz`() {
        TestThat(
            (SimpleReplaceCipher(
                "tec",
                "XFQABOLYWJGPMRVIHUSDZKNTEC".lowercase(Locale.getDefault())
            ).decrypt()
                .getOrNull() as ResultMessage.IdMessage)
                .args.first()
        )
            .assert("xyz")
    }
}