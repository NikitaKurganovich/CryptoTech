
import dev.crypto.desktop.lab.lab.generated.resources.Res
import dev.crypto.desktop.lab.lab.generated.resources.empty_warning
import dev.crypto.first.domain.Check
import dev.crypto.first.domain.checkMessage
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString
import org.junit.Test

class CheckMessageTest {
    @Test
    fun `test check message`() {
        TestThat(checkMessage(
            string = "test",
        ) {
            "test"
        }).assert("test")
    }

    @Test
    fun `test check message with additional checks`() {
        TestThat(checkMessage(
            Check(false) {
                "new test"
            },
            string = "test",
        ) {
            "old test"
        }).assert("old test")
    }

    @Test
    fun `test check message with additional checks and failed`() {
        TestThat(checkMessage(
            Check(true) {
                "new test"
            },
            string = "test",
        ) {
            "old test"
        }).assert("new test")
    }

    @Test
    fun `test check message with additional checks and failed and empty string`() = runBlocking{
        TestThat(kotlin.runCatching {
            checkMessage(
                Check(true) {
                    "new test"
                },
                string = "",
            ) {
                "old test"
            }
        }).assertWithErrorMessage<String>(getString(Res.string.empty_warning))
    }

    @Test
    fun `test with error in check`(){
        TestThat(kotlin.runCatching {
            checkMessage(
                Check(true) {
                    error("new test")
                },
                string = "test",
            ) {
                "old test"
            }
        }).assertWithErrorMessage<String>("new test")
    }
}