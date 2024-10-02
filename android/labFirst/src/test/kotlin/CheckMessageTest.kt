import dev.example.crypto.domain.AdditionCheck
import dev.example.crypto.domain.Strings.empty_warning
import dev.example.crypto.domain.Strings.format_error
import dev.example.crypto.domain.checkMessage
import org.junit.Test

class CheckMessageTest{
    @Test
    fun `test run with check`() {
        val result = checkMessage(
            message = "test",
            block = { "test" }
        )
        assert(result == "test")
    }

    @Test
    fun `return empty warning`() {
        TestThat(
            kotlin.runCatching {
                checkMessage(
                    message = "",
                    block = { "test" }
                )
            }
        ).assertWithErrorMessage<String>(empty_warning)
    }

    @Test
    fun `return format error`() {
        TestThat(
            kotlin.runCatching {
                checkMessage(
                    message = "@434",
                    block = { "test" }
                )
            }
        ).assertWithErrorMessage<String>(format_error)
    }

    @Test
    fun `custom check`() {
        TestThat(
            checkMessage(
                message = "test",
                block = { "test" },
                additionalCheck = arrayOf(
                    AdditionCheck(
                        predicate = true,
                        action = { "PREDICATE" }
                    )
                )
            )
        ).assert("PREDICATE")
    }

    @Test
    fun `stop on first additional check`() {
        TestThat(
            checkMessage(
                AdditionCheck(
                    predicate = true,
                    action = { "PREDICATE" }
                ),
                AdditionCheck(
                    predicate = true,
                    action = { "SECOND" }
                ),
                message = "test",
                block = { "test" },
            )
        ).assert("PREDICATE")
    }

    @Test
    fun `stop on second additional check`() {
        TestThat(
            checkMessage(
                AdditionCheck(
                    predicate = false,
                    action = { "PREDICATE" }
                ),
                AdditionCheck(
                    predicate = true,
                    action = { "SECOND" }
                ),
                message = "test",
                block = { "test" },
            )
        ).assert("SECOND")
    }
}