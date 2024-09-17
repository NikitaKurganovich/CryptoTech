import domain.releazation.key.AlphabetCipherKey
import org.junit.Test

class AlphabetCipherKeyTest {

    @Test
    fun `simple string`() {
        TestThat(AlphabetCipherKey("hello my friend").formatKey().getOrNull()!!.isNotEmpty())
            .assert(true)
    }
    @Test
    fun `number`() {
        TestThat(AlphabetCipherKey("1").formatKey().getOrNull()!!.isNotEmpty())
            .assert(true)
    }

    @Test
    fun `special char`() {
        TestThat(AlphabetCipherKey("^").formatKey().getOrNull()!!.isNotEmpty())
            .assert(true)
    }

    @Test
    fun `empty`() {
        TestThat(AlphabetCipherKey("").formatKey().getOrNull()!!.isNotEmpty())
            .assert(true)
    }

    @Test
    fun `spaces`() {
        TestThat(AlphabetCipherKey("  ").formatKey().getOrNull()!!.isNotEmpty())
            .assert(true)
    }
}