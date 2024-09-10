import dev.bababnanick.crypto_decoding.generated.resources.Res
import dev.bababnanick.crypto_decoding.generated.resources.numbers_warning
import domain.releazation.ComplicatedPermutationCipherKey
import kotlinx.coroutines.runBlocking
import javax.swing.UIManager.getString
import kotlin.test.Test

class ComplicatedPermutationCipherKeyTest {

    @Test
    fun `negative number`() = runBlocking {
        TestThat(ComplicatedPermutationCipherKey("-2 7").formatKey().toString())
            .assertWithErrorMessage<String>("Length of key must be more than 1")
    }

}