import androidx.compose.ui.geometry.times
import dev.crypto.base.test.TestThat
import dev.crypto.labthird.BruteForceSpeed
import dev.crypto.labthird.GenerationOptions
import dev.crypto.labthird.PasswordStrengthRequirements
import dev.crypto.labthird.PasswordUnit
import dev.crypto.labthird.ThirdLabIntent
import dev.crypto.labthird.ThirdLabIntentHandler
import io.nacular.measured.units.Measure
import io.nacular.measured.units.Time
import io.nacular.measured.units.div
import io.nacular.measured.units.times
import org.junit.Before
import java.math.BigDecimal
import kotlin.test.Test

class IntentHandlerTest {

    private lateinit var intentHandler: ThirdLabIntentHandler

    private val password = PasswordUnit()
    private val hours = Time.hours

    private val testConfig = object : PasswordStrengthRequirements {
        override val probability: BigDecimal
            get() = BigDecimal.valueOf(1, -8)
        override val bruteForceSpeed: BruteForceSpeed
            get() = 100 * password / hours
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
    }


}