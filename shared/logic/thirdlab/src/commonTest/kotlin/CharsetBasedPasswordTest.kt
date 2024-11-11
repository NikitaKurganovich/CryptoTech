import dev.crypto.base.test.TestThat
import dev.crypto.labthird.CharsetBasedPassword
import dev.crypto.labthird.GenerationOptions
import dev.crypto.labthird.ThirdLabErrorResults
import org.junit.Test

class CharsetBasedPasswordTest {
    @Test
    fun `error on empty charset`(){
        TestThat(
            kotlin.runCatching {
                CharsetBasedPassword(
                    charset = listOf(),
                    requiredLength = 10
                ).generatePassword()
            }
        ).assertWithErrorMessage<String>(ThirdLabErrorResults.CharsetEmpty.name)
    }

    @Test
    fun `error on empty password length`(){
        TestThat(
            kotlin.runCatching {
                CharsetBasedPassword(
                    charset = GenerationOptions.Digits.charset,
                    requiredLength = 0
                ).generatePassword()
            }
        ).assertWithErrorMessage<String>(ThirdLabErrorResults.PasswordLengthTooShort.name)
    }

    @Test
    fun `password generated`(){
        val password = CharsetBasedPassword(
            charset = GenerationOptions.Digits.charset +
                GenerationOptions.Letters.charset +
                GenerationOptions.SpecialCharacters.charset,
            requiredLength = 10
        )
        println(password.generatePassword())
    }
}
