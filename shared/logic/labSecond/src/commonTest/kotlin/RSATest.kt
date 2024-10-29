import dev.crypto.base.test.TestThat
import dev.crypto.base.test.TestThat.ErorrType
import dev.crypto.labsecond.IntKeyTriplet
import dev.crypto.labsecond.RSA
import kotlin.test.Test

class RSATest {
    // region ENCRYPTION

    @Test
    fun `p = 3 q = 11 e = 7 message = cab and result = 9 1 29`(){
        TestThat(
            RSA(
                message = "cab",
                key = IntKeyTriplet(
                    p = 3,
                    q = 11,
                    e = 7
                )
            ).encrypt()
        ).assert(Result.success("9 1 29"))
    }

    @Test
    fun `p = 7 q = 5 e = 5 message = hello and result = 8 10 17 17 15`(){
        TestThat(
            RSA(
                message = "hello",
                key = IntKeyTriplet(
                    p = 7,
                    q = 5,
                    e = 5
                )
            ).encrypt()
        ).assert(Result.success("8 10 17 17 15"))
    }

    @Test
    fun `p = 11 q = 5 e = 7 message = doctor and result = 49 5 42 15 5 17`(){
        TestThat(
            RSA(
                message = "doctor",
                key = IntKeyTriplet(
                    p = 11,
                    q = 5,
                    e = 7
                )
            ).encrypt()
        ).assert(Result.success("49 5 42 15 5 17"))
    }

    @Test
    fun `error when p and q are not prime numbers`(){
        TestThat(
            RSA(
                message = "some message",
                key = IntKeyTriplet(
                    p = 4,
                    q = 9,
                    e = 11
                )
            ).encrypt()
        ).assertWithErrorMessage<String>(
            expectedError = "p and q must be prime numbers",
            type = ErorrType.IllegalArgumentException
        )
    }

    @Test
    fun `error when e is not coprime with phi`(){
        TestThat(
            RSA(
                message = "a",
                key = IntKeyTriplet(
                    p = 11,
                    q = 5,
                    e = 4
                )
            ).encrypt()
        ).assertWithErrorMessage<String>(
            expectedError = "e must be coprime with Φ = (p - 1) * (q - 1)",
            type = ErorrType.IllegalArgumentException
        )
    }

    // region DECRYPTION

    @Test
    fun `decrypting 9 1 29 with p = 3 q = 11 e = 7 gives message cab`(){
        TestThat(
            RSA(
                message = "9 1 29",
                key = IntKeyTriplet(
                    p = 3,
                    q = 11,
                    e = 7
                )
            ).decrypt()
        ).assert(Result.success("cab"))
    }

    @Test
    fun `decrypting 8 10 17 17 15 with p = 7 q = 5 e = 5 gives message hello`(){
        TestThat(
            RSA(
                message = "8 10 17 17 15",
                key = IntKeyTriplet(
                    p = 7,
                    q = 5,
                    e = 5
                )
            ).decrypt()
        ).assert(Result.success("hello"))
    }

    @Test
    fun `decrypting 49 5 42 15 5 17 with p = 11 q = 5 e = 7 gives message doctor`(){
        TestThat(
            RSA(
                message = "49 5 42 15 5 17",
                key = IntKeyTriplet(
                    p = 11,
                    q = 5,
                    e = 7
                )
            ).decrypt()
        ).assert(Result.success("doctor"))
    }

    @Test
    fun `error when message is improperly formatted`(){
        TestThat(
            RSA(
                message = "dfghError message??",
                key = IntKeyTriplet(
                    p = 7,
                    q = 11,
                    e = 5
                )
            ).decrypt()
        ).assertWithErrorMessage<String>(
            expectedError = "e must be coprime with Φ = (p - 1) * (q - 1)",
            type = ErorrType.IllegalArgumentException
        )
    }
}