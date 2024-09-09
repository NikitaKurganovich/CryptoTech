import domain.releazation.SimplePermutationCipher
import domain.releazation.VigenereCipher
import kotlin.test.Test

class SimplePermutationCipherTest {

    @Test
    fun `simple check`() {
        TestThat(SimplePermutationCipher("abc", listOf(2, 3, 1)).encrypt())
            .assert(Result.success("bca"))
    }
    @Test
    fun `complex check`() {
        TestThat(SimplePermutationCipher("encryption", listOf(3, 8, 1, 5, 2, 7, 6, 4, 10, 9)).encrypt())
            .assert(Result.success("cieyntprno"))
    }
}