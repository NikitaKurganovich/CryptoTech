import dev.crypto.base.test.TestThat
import dev.crypto.labsecond.CryptoMode
import dev.crypto.labsecond.StringMessage
import kotlin.test.Test

class StringMessageTest {
    @Test
    fun `text mode encryption`(){
        TestThat(
            StringMessage(
                value = "text",
                mode = CryptoMode.Encrypting
            ).formatMessage()
        ).assert(Result.success("text"))
    }

    @Test
    fun `numbers mode encryption`(){
        TestThat(
            StringMessage(
                value = "1 2",
                mode = CryptoMode.Encrypting
            ).formatMessage()
        ).assertWithErrorMessage<String>("MessageFormat")
    }

    @Test
    fun `text with space mode encryption`(){
        TestThat(
            StringMessage(
                value = "tex t",
                mode = CryptoMode.Encrypting
            ).formatMessage()
        ).assertWithErrorMessage<String>("MessageFormat")
    }

    @Test
    fun `text mode decryption`(){
        TestThat(
            StringMessage(
                value = "text",
                mode = CryptoMode.Decrypting
            ).formatMessage()
        ).assertWithErrorMessage<String>("MessageFormat")
    }

    @Test
    fun `numbers mode decryption`(){
        TestThat(
            StringMessage(
                value = "1 2",
                mode = CryptoMode.Decrypting
            ).formatMessage()
        ).assert(Result.success("1 2"))
    }

    @Test
    fun `text with space mode decryption`(){
        TestThat(
            StringMessage(
                value = "tex t",
                mode = CryptoMode.Decrypting
            ).formatMessage()
        ).assertWithErrorMessage<String>("MessageFormat")
    }
}