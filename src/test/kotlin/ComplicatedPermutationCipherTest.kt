import domain.releazation.CesarCipher
import domain.releazation.ComplicatedPermutationCipher
import kotlin.test.Test

class ComplicatedPermutationCipherTest {
    @Test
    fun `a became b`() {
        TestThat(
            ComplicatedPermutationCipher(
                message = "encryption",
                key = Pair<List<Int>, List<Int>>(
                    listOf(5, 3, 1, 2, 4),
                    listOf(2, 1)
                )
            ).encrypt()
        )
            .assert(Result.success("niptoycenr"))
    }
}