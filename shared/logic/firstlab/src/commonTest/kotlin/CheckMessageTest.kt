import dev.crypto.base.test.TestThat
import dev.crypto.labfirst.Check
import dev.crypto.labfirst.FirstLabErrors
import dev.crypto.labfirst.checkMessage
import org.junit.Test

class CheckMessageTest{
    @Test
    fun `test run with check`() {
        val result = checkMessage(
            string = "test",
            block = { "test" }
        )
        assert(result == "test")
    }

    @Test
    fun `return empty warning`() {
        TestThat(
            kotlin.runCatching {
                checkMessage(
                    string = "",
                    block = { "test" }
                )
            }
        ).assertWithErrorMessage<String>(FirstLabErrors.MessageEmpty.name)
    }

    @Test
    fun `return format error`() {
        TestThat(
            kotlin.runCatching {
                checkMessage(
                    string = "@434",
                    block = { "test" }
                )
            }
        ).assertWithErrorMessage<String>(FirstLabErrors.MessageFormat.name)
    }

    @Test
    fun `custom check`() {
        TestThat(
            checkMessage(
                string = "test",
                block = { "test" },
                additionalChecks = arrayOf(
                    Check(
                        predicate = true,
                        onFailed = { "PREDICATE" }
                    )
                )
            )
        ).assert("PREDICATE")
    }

    @Test
    fun `stop on first additional check`() {
        TestThat(
            checkMessage(
                Check(
                    predicate = true,
                    onFailed = { "PREDICATE" }
                ),
                Check(
                    predicate = true,
                    onFailed = { "SECOND" }
                ),
                string = "test",
                block = { "test" },
            )
        ).assert("PREDICATE")
    }

    @Test
    fun `stop on second additional check`() {
        TestThat(
            checkMessage(
                Check(
                    predicate = false,
                    onFailed = { "PREDICATE" }
                ),
                Check(
                    predicate = true,
                    onFailed = { "SECOND" }
                ),
                string = "test",
                block = { "test" },
            )
        ).assert("SECOND")
    }
}