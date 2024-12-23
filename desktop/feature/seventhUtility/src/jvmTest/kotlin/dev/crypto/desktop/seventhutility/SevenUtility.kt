package dev.crypto.desktop.seventhutility

import dev.crypto.base.resources.ResultMessage
import dev.crypto.labsecond.IntKeyTriplet
import java.math.BigDecimal
import kotlin.test.Test

/**
 *  This class have to be test environment, as all values won't mutate
 */
class SevenUtility {
    private val rsa = RSACalculation(
        IntKeyTriplet(
            p = 17,
            q = 23,
            e = 29
        )
    )
    private val rArray = arrayOf(37, 60, 111, 180, 228)

    private val hiddenNumber = 279.toBigDecimal()

    private val ignoredIndex = 1

    @Test
    fun `generate keys`() {
        val eAndN = (rsa.encrypt().getOrNull() as ResultMessage.StringMessage).message.splitToSequence(",")

        eAndN.let {
            println("{e, n}: {${it.first()}, ${it.last()}}")
        }
        val dAndNAndPhi = (rsa.decrypt().getOrNull() as ResultMessage.StringMessage).message.splitToSequence(",").toList()

        dAndNAndPhi.let {
            println("{d, n, phi} = {${it.first()}, ${it[1]}, ${it.last()}}")
        }
        val e = eAndN.first().toInt()
        val n = eAndN.last().toBigDecimal()
        val d = dAndNAndPhi.first().toInt()

        val cList = `find c list`(e = e, n = n)
        cList.forEachIndexed { i: Int, bigDecimal: BigDecimal ->
            println("C${i + 1} = $bigDecimal")
        }

        println("Finding reciprocal numbers: ")
        val reciprocalMap = `find reciprocal numbers as map`(n)
        reciprocalMap.forEach {
            println("R${it.key} = ${it.value}")
        }

        println("Trying to realise envelop: ")
        val realisedEnvelop = `realized envelop`(
            cList = cList,
            n = n,
            e = e,
            reciprocalMap = reciprocalMap
        )
        realisedEnvelop.forEach {
            println("M${it.key} = ${it.value}")
        }
        `print guess status`(realisedEnvelop)

        println("Buyer signs:")
        `blind sign`(
            n = n,
            e = e,
            d = d,
        )
    }

    private fun `print guess status`(
        realisedEnvelop: Map<Int, BigDecimal>
    ) = println(
        if (!realisedEnvelop.containsValue(hiddenNumber)) {
            "Number didn't guessed!"
        } else "Number guessed!"
    )

    private fun `find c list`(
        e: Int,
        n: BigDecimal
    ): List<BigDecimal> =
        rArray.map {
            (hiddenNumber * it.toBigDecimal().pow(e)) % n
        }

    private fun `find reciprocal numbers as map`(
        n: BigDecimal,
    ): Map<Int, BigDecimal> {
        val numberMap = buildMap {
            rArray.forEachIndexed { index, i ->
                if (index != ignoredIndex) {
                    put(key = index, value = i.toBigDecimal())
                }
            }
        }
        return numberMap.map { num ->
            num.key to num.value.findReciprocal(n)
        }.toMap()
    }

    private fun BigDecimal.findReciprocal(
        n: BigDecimal,
    ) = generateSequence(1) { it + 1 }
        .first { (it.toBigDecimal() * this) % n == 1.toBigDecimal() }
        .toBigDecimal()



    private fun `realized envelop`(
        reciprocalMap: Map<Int, BigDecimal>,
        cList: List<BigDecimal>,
        e: Int,
        n: BigDecimal
    ) = reciprocalMap.map { num ->
        num.key to cList[num.key] * (num.value.pow(e)) % n
    }.toMap()

    private fun `blind sign`(
        d: Int,
        e: Int,
        n: BigDecimal,
    ) {
        val blindSign = 379.toBigDecimal().pow(d) % n
        println("Bank's blind sign: $blindSign")
        val reciprocalIgnored = rArray[ignoredIndex].toBigDecimal().findReciprocal(n)
        println("Reciprocal ignored: $reciprocalIgnored")
        val mask = (blindSign * reciprocalIgnored) % n
        println("Buyer's realised mask: $mask ")
        println("Buyer got they staff. And this is ${mask.pow(e) % n == hiddenNumber}")
        assert(mask.pow(e) % n == hiddenNumber)
    }
}