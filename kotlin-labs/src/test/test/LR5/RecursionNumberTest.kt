package LR5

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class RecursionNumberTest {

    @Test
    fun testFindMinDigit() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(2, recursionFunc.findMinDigit(234))
        assertEquals(0, recursionFunc.findMinDigit(0))
        assertEquals(1, recursionFunc.findMinDigit(54321))
        assertEquals(3, recursionFunc.findMinDigit(999939939))
        assertEquals(1, recursionFunc.findMinDigit(12345))
        assertEquals(2, recursionFunc.findMinDigit(-23456))
        assertEquals(5, recursionFunc.findMinDigit(5))
        assertEquals(0, recursionFunc.findMinDigit(1000))
        assertEquals(1, recursionFunc.findMinDigit(1234))
    }

    @Test
    fun testFindMinDigitTailRec() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(2, recursionFunc.findMinDigitTailRec(234))
        assertEquals(0, recursionFunc.findMinDigitTailRec(0))
        assertEquals(1, recursionFunc.findMinDigitTailRec(54321))
        assertEquals(3, recursionFunc.findMinDigitTailRec(999939939))
        assertEquals(1, recursionFunc.findMinDigit(12345))
        assertEquals(2, recursionFunc.findMinDigit(-23456))
        assertEquals(5, recursionFunc.findMinDigit(5))
        assertEquals(0, recursionFunc.findMinDigit(1000))
        assertEquals(1, recursionFunc.findMinDigit(1234))
    }

    @Test
    fun testProductOfNonDivisibleBy5() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(6, recursionFunc.productOfNonDivisibleBy5(123))
        assertEquals(32, recursionFunc.productOfNonDivisibleBy5(854))
        assertEquals(0, recursionFunc.productOfNonDivisibleBy5(555))
        assertEquals(0, recursionFunc.productOfNonDivisibleBy5(0))
        assertEquals(1, recursionFunc.productOfNonDivisibleBy5(1))
        assertEquals(32, recursionFunc.productOfNonDivisibleBy5(-854))
    }

    @Test
    fun testProductOfNonDivisibleBy5Tail() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(6, recursionFunc.productOfNonDivisibleBy5Tail(123))
        assertEquals(32, recursionFunc.productOfNonDivisibleBy5Tail(854))
        assertEquals(0, recursionFunc.productOfNonDivisibleBy5Tail(555))
        assertEquals(0, recursionFunc.productOfNonDivisibleBy5Tail(0))
        assertEquals(1, recursionFunc.productOfNonDivisibleBy5Tail(1))
        assertEquals(32, recursionFunc.productOfNonDivisibleBy5Tail(-854))
    }

    @Test
    fun testNod() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(12, recursionFunc.nod(24, 36))
        assertEquals(5, recursionFunc.nod(15, 10))
        assertEquals(7, recursionFunc.nod(14, 21))
        assertEquals(2, recursionFunc.nod(6, 8))
        assertEquals(5, recursionFunc.nod(5, 0))
        assertEquals(3, recursionFunc.nod(-9, -6))
        assertEquals(1, recursionFunc.nod(7, 4))
    }

    @Test
    fun findMax() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(
            32,
            recursionFunc.findMax(
                listOf(123, 854, 555), recursionFunc::productOfNonDivisibleBy5Tail)
        )
        assertEquals(
            5,
            recursionFunc.findMax(
                listOf(123, 854, 555),
                recursionFunc::findMinDigitTailRec
            )
        )
    }

    @Test
    fun maxPrimeDivision() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(17, recursionFunc.maxPrimeDivisor(68))
        assertEquals(17, recursionFunc.maxPrimeDivisor(51))
        assertEquals(3, recursionFunc.maxPrimeDivisor(24))
        assertEquals(2, recursionFunc.maxPrimeDivisor(4))
        assertEquals(17, recursionFunc.maxPrimeDivisor(17))
        assertEquals(3, recursionFunc.maxPrimeDivisor(-24))
    }

    @Test
    fun calculateNodAndProductWithOddNonPrimeDivisor() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(1, recursionFunc.calculateNodAndProductWithOddNonPrimeDivisor(24))
        assertEquals(3, recursionFunc.calculateNodAndProductWithOddNonPrimeDivisor(30))
        assertEquals(1, recursionFunc.calculateNodAndProductWithOddNonPrimeDivisor(1))
    }

    @Test
    fun maxPeriod() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(983, recursionFunc.maxPeriod(0, 0))
    }

    @Test
    fun forDigits() {
        val recursionFunc = RecursionNumberFunctions()
        assertEquals(
            10,
            recursionFunc.forDigits(
                18572,
                { total, digit -> total + digit },
                { it % 2 == 0 }
                )
        )

        assertEquals(
            35,
            recursionFunc.forDigits(
                272454,
                { total, digit -> total * digit},
                { it % 2 != 0 },
                1
            )
        )

        assertEquals(
            5,
            recursionFunc.forDigits(
                98576,
                { total, digit -> if (digit < total) digit else total},
                { true },
                9
            )
        )
    }

}