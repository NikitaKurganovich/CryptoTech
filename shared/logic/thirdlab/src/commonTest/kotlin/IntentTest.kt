import dev.crypto.base.test.TestThat
import dev.crypto.labthird.BruteForceSpeed
import dev.crypto.labthird.GenerationOptions
import dev.crypto.labthird.PasswordStrengthRequirements
import dev.crypto.labthird.PasswordUnit.Companion.password
import dev.crypto.labthird.ThirdLabIntent
import dev.crypto.labthird.ThirdLabIntentHandler
import dev.crypto.labthird.TimeWithDays.Companion.day
import io.nacular.measured.units.Measure
import io.nacular.measured.units.Time
import io.nacular.measured.units.Time.Companion.hours
import io.nacular.measured.units.div
import io.nacular.measured.units.times
import org.junit.Before
import java.math.BigDecimal
import kotlin.test.Test

class IntentHandlerTest {
    private lateinit var intentHandler: ThirdLabIntentHandler

    private val testConfig = object : PasswordStrengthRequirements {
        override val probability: BigDecimal
            get() = BigDecimal.valueOf(1, 8)
        override val bruteForceSpeed: BruteForceSpeed
            get() = 100 * password / day
        override val timeToBruteForce: Measure<Time>
            get() = 20 * hours
    }

    @Before
    fun setup() {
        intentHandler = ThirdLabIntentHandler(testConfig)
    }

    @Test
    fun `on add isDigits method success`() {
        intentHandler.processIntent(
            ThirdLabIntent.SetGenerationOption(
                GenerationOptions.Digits
            )
        )
        TestThat(
            intentHandler.state.value.isDigitsSelected
        ).assert(true)
        TestThat(
            intentHandler.state.value.isSpecialCharactersSelected
        ).assert(false)
        TestThat(
            intentHandler.state.value.isLettersSelected
        ).assert(false)
    }

    @Test
    fun `on add isSpecialCharacters method success`() {
        intentHandler.processIntent(
            ThirdLabIntent.SetGenerationOption(
                GenerationOptions.SpecialCharacters
            )
        )
        TestThat(
            intentHandler.state.value.isSpecialCharactersSelected
        ).assert(true)
        TestThat(
            intentHandler.state.value.isDigitsSelected
        ).assert(false)
        TestThat(
            intentHandler.state.value.isLettersSelected
        ).assert(false)
    }

    @Test
    fun `on add isLetters method success`() {
        intentHandler.processIntent(
            ThirdLabIntent.SetGenerationOption(
                GenerationOptions.Letters
            )
        )
        TestThat(
            intentHandler.state.value.isLettersSelected
        ).assert(true)
        TestThat(
            intentHandler.state.value.isDigitsSelected
        ).assert(false)
        TestThat(
            intentHandler.state.value.isSpecialCharactersSelected
        ).assert(false)
    }

    @Test
    fun `on all options selected success`() {
        intentHandler.processIntent(
            ThirdLabIntent.AllOptionsSelected
        )
        TestThat(
            intentHandler.state.value.isDigitsSelected
        ).assert(true)
        TestThat(
            intentHandler.state.value.isSpecialCharactersSelected
        ).assert(true)
        TestThat(
            intentHandler.state.value.isLettersSelected
        ).assert(true)
    }

    @Test
    fun `on all options selected again success`() {
        intentHandler.processIntent(
            ThirdLabIntent.AllOptionsSelected
        )
        intentHandler.processIntent(
            ThirdLabIntent.AllOptionsSelected
        )
        TestThat(
            intentHandler.state.value.isDigitsSelected
        ).assert(false)
        TestThat(
            intentHandler.state.value.isSpecialCharactersSelected
        ).assert(false)
        TestThat(
            intentHandler.state.value.isLettersSelected
        ).assert(false)
    }

    @Test
    fun `on generate password success`() {
        intentHandler.processIntent(
            ThirdLabIntent.AllOptionsSelected
        )
        intentHandler.processIntent(
            ThirdLabIntent.GeneratePassword
        )
        TestThat(
            intentHandler.state.value.resultMessage
        ).assertNotNull()
        println("Password length: ${intentHandler.state.value.passwordLength}")
        println("Password: ${intentHandler.state.value.resultMessage}")
    }

    @Test
    fun `digits charset for correctly updated`() {
        intentHandler.processIntent(
            ThirdLabIntent.SetGenerationOption(
                GenerationOptions.Digits
            )
        )
        TestThat(
            intentHandler.state.value.actualCharset
        ).assert(GenerationOptions.Digits.charset)
    }

    @Test
    fun `special characters charset for correctly updated`() {
        intentHandler.processIntent(
            ThirdLabIntent.SetGenerationOption(
                GenerationOptions.SpecialCharacters
            )
        )
        TestThat(
            intentHandler.state.value.actualCharset
        ).assert(GenerationOptions.SpecialCharacters.charset)
    }

    @Test
    fun `letters charset for correctly updated`() {
        intentHandler.processIntent(
            ThirdLabIntent.SetGenerationOption(
                GenerationOptions.Letters
            )
        )
        TestThat(
            intentHandler.state.value.actualCharset
        ).assert(GenerationOptions.Letters.charset)
    }

    @Test
    fun `all charset for correctly updated`() {
        intentHandler.processIntent(
            ThirdLabIntent.AllOptionsSelected
        )
        TestThat(
            intentHandler.state.value.actualCharset
        ).assert(GenerationOptions.Digits.charset + GenerationOptions.SpecialCharacters.charset + GenerationOptions.Letters.charset)
    }

    @Test
    fun `password length change on options change`() {
        intentHandler.processIntent(
            ThirdLabIntent.SetGenerationOption(
                GenerationOptions.Digits
            )
        )
        TestThat(
            intentHandler.state.value.passwordLength
        ).assert(9)
        intentHandler.processIntent(
            ThirdLabIntent.SetGenerationOption(
                GenerationOptions.Letters
            )
        )
        TestThat(
            intentHandler.state.value.passwordLength
        ).assert(5)
    }
}